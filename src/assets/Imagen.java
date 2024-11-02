package assets;

import java.net.URL;

public class Imagen {
    
    public URL success(){
        return getClass().getResource("success.png");
    }
    
    public URL error(){
        return getClass().getResource("error.png");
    }
}
