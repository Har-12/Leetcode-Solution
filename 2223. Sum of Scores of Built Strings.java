class Solution {
    public long sumScores(String s) {
        int n = s.length();
        char[] ss = s.toCharArray();
        int[] z = new int[n];
        int l = 0;
        int r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < n && ss[z[i]] == ss[i + z[i]]) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        long sum = n;
        for (int i = 0; i < n; i++) {
            sum += z[i];
        }
        return sum;
    }
