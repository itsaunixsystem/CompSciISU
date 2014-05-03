import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.itsaunixsystem.chunks.ChunksGame;

public class DesktopStarter {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "CHUNKSDEV";
        cfg.width = 800;
        cfg.height = 480;
        cfg.useGL30 = false;
        new LwjglApplication(new ChunksGame(), cfg);
    }
}
