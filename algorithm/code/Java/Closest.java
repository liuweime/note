import java.awt.*;
import java.util.*;
import java.util.List;

public class Closest {

    public double[] count(double[] list)
    {
        double[] data = new double[2];
        Arrays.sort(list);

        double min = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < list.length-1; i++) {
            if (min > Math.abs(list[i] - list[i+1])) {
                min = Math.abs(list[i] - list[i+1]);
                index = i;
            }
        }
        data[0] = list[index];
        data[1] = list[index+1];
        return data;
    }

    private double distance(Point pointA, Point pointB)
    {
        int m = Math.abs(pointA.getX() - pointB.getX());
        int n = Math.abs(pointA.getY() - pointB.getY());

        return Math.sqrt(Math.pow(m,2)+Math.pow(n,2));
    }

    public Point[] buildPoint(int num)
    {
        Point[] data = new Point[num];
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            data[i] = new Point(random.nextInt(num)+1, random.nextInt(num)+1);
        }

        return data;
    }

    public double pair(Point[] list) throws Exception {
        if (list.length < 2) {
            throw new Exception("error point");
        }

        // 边界
        if (list.length == 2) {
            // 返回点距
            return distance(list[0], list[1]);
        }

        if (list.length == 3) {
            double a = distance(list[0], list[1]);
            double b = distance(list[0], list[2]);
            double c = distance(list[1], list[2]);
            double min = Math.min(a, b);
            if (min > c) {
                min = c;
            }
            return min;
        }

        // 大于2的集合分治法计算点距
        // 对点集按照x轴进行排序
        Arrays.sort(list, new PointComparator());
        // 分成左右部分
        int middleX = list[(list.length-1)/2].getX();

        int leftLen = list.length/2;
        int rightLen = list.length - leftLen;
        Point[] leftPoint = new Point[leftLen];
        Point[] rightPoint = new Point[rightLen];
        for (int i = 0; i < leftLen; i++) {
            leftPoint[i] = list[i];
        }
        for (int j = 0, i = leftLen; i < list.length; i++) {
            rightPoint[j++] = list[i];
        }
        // 左侧最短
        double distenceLeft = pair(leftPoint);
        // 右侧最短
        double distenceRight = pair(rightPoint);
        // 获取最短距离
        double distence = Math.min(distenceLeft, distenceRight);

        // 合并左右区间
        // (x)距离中线距离小于等于左右区间中最短距离的是最接近点对的待选点集
//        Point[] data = new Point[list.length];
        List<Point> data = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < list.length; i++) {
            if (Math.abs(list[i].getX() - middleX) <= distence) {
                data.add(list[i]);
                j++;
            }
        }
        // 根据y进行排序
        Collections.sort(data, new PointComparatorY());

//        Arrays.sort(data, new PointComparatorY());
        // 根据鸽笼理论，只需检测6个点
        for (int i = 0; i < j; i++) {
            for (int m = i+1; m < j && data.get(m).getY() - data.get(i).getY() < distence; m++) {
                double distanceRes = distance(data.get(m), data.get(i));
                if (distanceRes < distence) {
                    distence = distanceRes;
                }
            }
        }

        return distence;
    }
}

class PointComparator implements Comparator<Point>
{
    @Override
    public int compare(Point o1, Point o2) {
        return o1.getX() - o2.getX();
    }
}

class PointComparatorY implements Comparator<Point>
{
    @Override
    public int compare(Point o1, Point o2) {
        return o1.getY() - o2.getY();
    }
}

class Point
{
    private int x;
    private int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}