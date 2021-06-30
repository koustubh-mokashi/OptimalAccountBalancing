class Solution {
        public int minTransfers(int[][] transactions) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int []t:transactions) {
            map.put(t[0],map.getOrDefault(t[0],0)+t[2]);
            map.put(t[1],map.getOrDefault(t[1],0)-t[2]);
        }

        List<Integer> list = map.values().stream().filter(v->v!=0).collect(Collectors.toList());

        return dfs(0,list);
    }

    private int dfs(int i, List<Integer> list) {
        if(i == list.size())
            return 0;
        int cur = list.get(i);
        if(cur == 0) {
            return dfs(i+1, list);
        }
        int min = Integer.MAX_VALUE;
        for(int k=i+1;k<list.size();k++) {
            int next = list.get(k);
            if(cur * next < 0) {
                list.set(k, cur+next);
                min = Math.min(min, 1 + dfs(i+1, list));
                list.set(k, next);
                if(cur + next == 0)
                    break;
            }
        }
        return min;
    }
    
}
