class Solution {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        if (n == 1) {
            return plantTime[0] + growTime[0];
        }
        Seed[] arr = new Seed[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Seed(plantTime[i], growTime[i]);
        }
        Arrays.sort(arr, Collections.reverseOrder());
        int ans = arr[0].plantTime + arr[0].growTime;
        int lastPlantDay = arr[0].plantTime;
        for (int i = 1; i < n; i++) {
            int currBloomDay = lastPlantDay + arr[i].plantTime + arr[i].growTime;
            ans = Math.max(ans, currBloomDay);
            lastPlantDay += arr[i].plantTime;
        }
        return ans;
    }

    static class Seed implements Comparable<Seed> {
        int plantTime;
        int growTime;

        Seed(int plantTime, int growTime) {
            this.plantTime = plantTime;
            this.growTime = growTime;
        }

        public int compareTo(Seed s) {
            return this.growTime - s.growTime;
        }
    }
}
