// OJ: https://leetcode.com/problems/maximum-earnings-from-taxi/
// Time: O(N + M) where `N` is number of points and `M` is the length of `A`.
// Space: O(N)
class Solution {
public:
    long long maxTaxiEarnings(int n, vector<vector<int>>& A) {
        vector<vector<pair<int, int>>> rideStartAt(n); // group all the rides starting at the same time
        for (auto &ride : A) {
            int s = ride[0], e = ride[1], t = ride[2];
            rideStartAt[s].push_back({ e, e - s + t });  // [end, dollar]
        }
        vector<long long> dp(n + 1);
        for (int i = n - 1; i >= 1; --i) { // Traverse the rides in descending order of start time
            for (auto &[e, d] : rideStartAt[i]) {
                dp[i] = max(dp[i], dp[e] + d);
            }
            dp[i] = max(dp[i], dp[i + 1]);
        }
        return dp[1];
    }
};
