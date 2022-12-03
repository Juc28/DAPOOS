package dominio;

public class Elemento {
    private String color;
    private Integer x;
    private Integer y;
    private String imagen;

    public Elemento(String color,Integer x, Integer y) {
        this.x = x;
        this.y = y;
        setColor(color);
    }

    public Elemento() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


}
