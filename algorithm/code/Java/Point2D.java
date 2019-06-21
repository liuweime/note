public class Point2D
{
    private int x;

    private int y;

    public Point2D(int x, int y)
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

    public int distance(Point2D point)
    {
        int m = Math.abs(point.x - this.x);
        int n = Math.abs(point.y - this.y);

        return (int)Math.sqrt(Math.pow(m,2)+Math.pow(n,2));
    }
}