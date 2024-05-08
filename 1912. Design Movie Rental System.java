import java.util.*;

class MovieRentingSystem {
    private Map<Integer, Set<int[]>> available; // Available movies and their rental price
    private Map<String, Map<Integer, Integer>> rented; // Rented movies, customer ID and rental price
    private PriorityQueue<int[]> cheapest; // Cheapest k rented movies

    public MovieRentingSystem(int n, int[][] entries) {
        available = new HashMap<>();
        rented = new HashMap<>();
        cheapest = new PriorityQueue<>((a, b) -> a[2] == b[2] ? a[0] - b[0] : a[2] - b[2]);
        
        for (int[] entry : entries) {
            int movie = entry[0];
            int shop = entry[1];
            int price = entry[2];
            
            available.computeIfAbsent(movie, k -> new HashSet<>()).add(new int[] {shop, price});
        }
    }
    
    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (available.containsKey(movie)) {
            for (int[] shopPrice : available.get(movie)) {
                res.add(shopPrice[0]);
            }
        }
        return res;
    }
    
    public void rent(int movie, int shop, int userId) {
        int[] rent = new int[] {movie, shop, available.get(movie).stream().filter(sp -> sp[0] == shop).findFirst().get()[1]};
        cheapest.offer(rent);
        available.get(movie).remove(rent);
        rented.computeIfAbsent(userId + "", k -> new HashMap<>()).put(movie, rent[2]);
    }
    
    public void drop(int movie, int shop, int userId) {
        int price = rented.get(userId + "").remove(movie);
        available.computeIfAbsent(movie, k -> new HashSet<>()).add(new int[] {shop, price});
        cheapest.removeIf(rent -> rent[0] == movie && rent[1] == shop);
    }
    
    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        while (res.size() < 5 && !cheapest.isEmpty()) {
            int[] rent = cheapest.poll();
            int movie = rent[0], shop = rent[1], price = rent[2];
            res.add(Arrays.asList(movie, shop));
            rented.get(getCustomer(rent)).put(movie, price);
        }
        return res;
    }
    
    private String getCustomer(int[] rent) {
        int movie = rent[0], shop = rent[1];
        for (Map.Entry<String, Map<Integer, Integer>> entry : rented.entrySet()) {
            if (entry.getValue().containsKey(movie) && entry.getValue().get(movie) == rent[2]) {
                return entry.getKey();
            }
        }
        return null;
    }
}
