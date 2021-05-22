import java.io.IOException;

public interface ICRUD {
    void add() throws IOException;
    void get() throws IOException;
    void update() throws IOException;
    void delete() throws IOException;
}
