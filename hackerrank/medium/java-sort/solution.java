

class Checker implements Comparator<Player> {

    @Override
    public int compare(Player a, Player b) {

        // Higher score comes first
        if (a.score != b.score) {
            return b.score - a.score;
        }

        // Same score: alphabetical order by name
        return a.name.compareTo(b.name);
    }
}

