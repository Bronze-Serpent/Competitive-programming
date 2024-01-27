package com.barabanov.tinkoff.cource.java;

import java.util.*;


public class KatyasParty
{
    // с третьим тестом проблемы :(
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String[] katyaInfo = scanner.nextLine().split(" ");
        int numOfFriends = Integer.parseInt(katyaInfo[0]);
        int friendsPairs = Integer.parseInt(katyaInfo[1]);
        int katyasReserves = Integer.parseInt(katyaInfo[2]);

        Integer[] friendsInterestingness = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
        Integer[] friendsGluttony = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);

        // записываю информацию о друзь€х
        Map<Integer, Integer> friendsInteresMap = new HashMap<>(numOfFriends);
        Map<Integer, Integer> friendsGluttonyMap = new HashMap<>(numOfFriends);
        for (int friendNum = 1; friendNum <= numOfFriends; friendNum++)
        {
            friendsInteresMap.put(friendNum, friendsInterestingness[friendNum - 1]);
            friendsGluttonyMap.put(friendNum, friendsGluttony[friendNum - 1]);
        }

        // составл€ю записи о группах
        Map<Group, DragonInfoDTO> dragonGroups = new HashMap<>();
        for (int pairNum = 0; pairNum < friendsPairs; pairNum++)
        {
            Integer[] pair = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Integer::valueOf)
                    .toArray(Integer[]::new);

            boolean friendsGroupFound = false;
            for (Group group : dragonGroups.keySet())
            {
                if (group.compound.contains(pair[0]))
                {
                    if (!group.compound.contains(pair[1]))
                    {
                        dragonGroups.get(group).interestingness += friendsInteresMap.get(pair[1]);
                        dragonGroups.get(group).gluttony += friendsGluttonyMap.get(pair[1]);
                        group.compound.add(pair[1]);
                    }

                    friendsGroupFound = true;
                    break;
                }
                if (group.compound.contains(pair[1]))
                {
                    if (!group.compound.contains(pair[0]))
                    {
                        dragonGroups.get(group).interestingness += friendsInteresMap.get(pair[0]);
                        dragonGroups.get(group).gluttony += friendsGluttonyMap.get(pair[0]);
                        group.compound.add(pair[0]);
                    }

                    friendsGroupFound = true;
                    break;
                }
            }
            if (!friendsGroupFound)
            {
                dragonGroups.put(new Group(pair), new DragonInfoDTO(
                        friendsGluttonyMap.get(pair[0]) + friendsGluttonyMap.get(pair[1]),
                        friendsInteresMap.get(pair[0]) + friendsInteresMap.get(pair[1]))
                );
            }

            friendsInteresMap.remove(pair[0]);
            friendsInteresMap.remove(pair[1]);
        }

        // должен пройтись также по всем драконам, и если их нет ни в какой группе - добавить
        for (Map.Entry<Integer, Integer> withoutPair : friendsInteresMap.entrySet())
        {
            dragonGroups.put(new Group(withoutPair.getKey()), new DragonInfoDTO(
                    friendsGluttonyMap.get(withoutPair.getKey()),
                    withoutPair.getValue())
            );
        }

        // выбираю лучшую группу или набор из групп.... но про набор тут нет :/
        int maxInteres = 0;
        for (DragonInfoDTO groupInfo : dragonGroups.values())
        {
            if (groupInfo.gluttony <= katyasReserves && maxInteres < groupInfo.interestingness)
                maxInteres = groupInfo.interestingness;
        }
        System.out.println(maxInteres);
    }

    private static class DragonInfoDTO
    {
        private int gluttony;
        private int interestingness;

        private DragonInfoDTO(int gluttony, int interestingness)
        {
            this.gluttony = gluttony;
            this.interestingness = interestingness;
        }
    }

    private static class Group
    {
        private static int idCounter = 0;

        private final int id;
        private final Set<Integer> compound;

        @Override
        public boolean equals(Object obj)
        {
            if (!(obj instanceof Group))
                return false;

            return this.id == ((Group) obj).id;
        }

        @Override
        public int hashCode()
        {
            return Objects.hashCode(id);
        }

        private Group(Integer[] group)
        {
            this.compound = new HashSet<>(Set.of(group));
            this.id = idCounter++;
        }

        private Group(Integer member)
        {
            this.compound = new HashSet<>(Set.of(member));
            this.id = idCounter++;
        }
    }
}
