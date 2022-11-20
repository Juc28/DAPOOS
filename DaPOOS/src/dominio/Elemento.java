package dominio;

public class Elemento {
    private String color;
    private int x;
    private int y;
    private String imagen;

    public Elemento(String color, int x, int y, String imagen) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.imagen = imagen;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
