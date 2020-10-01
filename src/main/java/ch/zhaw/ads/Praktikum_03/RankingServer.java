package ch.zhaw.ads.Praktikum_03;

import ch.zhaw.ads.CommandExecutor;

import java.util.*;

public class RankingServer implements CommandExecutor {
    private final List<Competitor_ToDo> competitor = new ArrayList<>();

    @Override
    public String execute(String command) throws Exception {
        if (insertCompetitor(command)) {
            // Rangliste
            Collections.sort(competitor);
            //StringBuilder stringBuilder = new StringBuilder();
            //stringBuilder.append("Nr Name,Vorname  JG   Wohnort   Zeit\n");
            for (int rank = 1; rank < competitor.size(); rank++) {
                Competitor_ToDo competitor_toDo = competitor.get(rank);
                competitor_toDo.setRank(rank);
                //stringBuilder.append(competitor_toDo.toString());
                //stringBuilder.append("\n");
            }
            NamelistCompare namelistCompare = new NamelistCompare();
            Collections.sort(competitor, namelistCompare);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Nr Name,Vorname  JG   Wohnort   Zeit\n");
            for (int rank = 1; rank < competitor.size(); rank++) {
                Competitor_ToDo competitor_toDo = competitor.get(rank);
                stringBuilder.append(competitor_toDo.toString());
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        } else {
            return "Not worked";
        }
    }

    private boolean insertCompetitor(String args) {
        String[] lines = args.split("\n");
        Iterator<String> iterator = Arrays.stream(lines).iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            String[] elements = line.split(";");
            competitor.add(new Competitor_ToDo(Integer.parseInt(elements[0]),
                    elements[1],
                    Integer.parseInt(elements[2]),
                    elements[3],
                    elements[4]));
        }
        return true;
    }
}
