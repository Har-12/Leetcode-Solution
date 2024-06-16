class Solution {
    public int countLatticePoints(int[][] circles) {
        int xMin = 200;
        int xMax = -1;
        int yMin = 200;
        int yMax = -1;
        for (int[] c : circles) {
            xMin = Math.min(xMin, c[0] - c[2]);
            xMax = Math.max(xMax, c[0] + c[2]);
            yMin = Math.min(yMin, c[1] - c[2]);
            yMax = Math.max(yMax, c[1] + c[2]);
        }
        int ans = 0;
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                for (int[] c : circles) {
                    if ((c[0] - x) * (c[0] - x) + (c[1] - y) * (c[1] - y) <= c[2] * c[2]) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
