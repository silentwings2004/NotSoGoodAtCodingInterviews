package S0_Templates;

public class CrossProduct {
    private int orientation(int[] p1, int[] p2, int[] p3) {
        return (p3[0] - p2[0]) * (p2[1] - p1[1]) - (p2[0] - p1[0]) * (p3[1] - p2[1]); // < 0 逆时针； > 0 顺时针
    }
}
/**
 * p: (x1, y1)  一开始的点
 * q: (x2, y2)  要考虑的点
 * r: (x3, y3)  参照点
 * pslope = y1 / x1;
 * qslope = y2 / x2;
 * y2 * x1 - y1 * x1 > 0 => qslope > pslope   q点在p点的逆时针方向上
 * 3个点：p, q, r
 * 如何判断在顺时针还是逆时针方向上呢？
 * 两个点变换成一个向量的问题进行处理
 * 把原来的pq两点变成向量
 * pq: (x2 - x1), (y2 - y1)
 * qr: (x3 - x2), (y3 - y2)
 * => cross product = (x2 - x1) * (y3 - y2) - (x3 - x2) * (y2 - y1) > 0 逆时针 来判断qr是在pr的左边还是右边
 *                                                                  < 0 顺时针
 */