package jiang.pojo;

public class Gif {
    private int id;
    private String biaoqingbao;


    public Gif() {
    }

    public Gif(int id, String biaoqingbao) {
        this.id = id;
        this.biaoqingbao = biaoqingbao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBiaoqingbao() {
        return biaoqingbao;
    }

    public void setBiaoqingbao(String biaoqingbao) {
        this.biaoqingbao = biaoqingbao;
    }

    @Override
    public String toString() {
        return "Gif{" +
                "id=" + id +
                ", biaoqingbao='" + biaoqingbao + '\'' +
                '}';
    }

}
