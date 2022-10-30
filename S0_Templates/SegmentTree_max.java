package S0_Templates;

private class SegTreeNode {
    SegTreeNode left, right;
    int start, end;
    int info;
    boolean tag;

    public SegTreeNode(int a, int b, int val) {
        start = a;
        end = b;
        tag = false;
        if (a == b) {
            info = val;
            return;
        }

        int mid = (a + b) / 2;
        if (left == null) {
            left = new SegTreeNode(a, mid, val);
            right = new SegTreeNode(mid + 1, b, val);
            info = Math.max(left.info, right.info);
        }
    }

    private void pushDown() {
        if (tag == true && left != null) {
            left.info = info;
            right.info = info;
            left.tag = true;
            right.tag = true;
            tag = false;
        }
    }

    private void updateRange(int a, int b, int val) {
        if (b < start || a > end) return;
        if (a <= start && end <= b) {
            info = val;
            tag = true;
            return;
        }

        if (left != null) {
            pushDown();
            left.updateRange(a, b, val);
            right.updateRange(a, b, val);
            info = Math.max(left.info, right.info);
        }
    }

    private int queryRange(int a, int b) {
        if (b < start || a > end) return Integer.MIN_VALUE;
        if (a <= start && end <= b) return info;
        if (left != null) {
            pushDown();
            int res = Math.max(left.queryRange(a, b), right.queryRange(a, b));
            info = Math.max(left.info, right.info);
            return res;
        }
        return info;
    }
}    
