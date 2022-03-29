package renderer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Texture {

    private String filepath;
    private transient int texID;
    private int width, height;

    public Texture() {
        texID = -1;
        width = -1;
        height = -1;

    }

    public Texture(String filepath) {
        this.filepath = filepath;

        texID = glGenTextures();  //Generate texture on GPU
        glBindTexture(GL_TEXTURE_2D, texID);

        //Texture Parameters
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);  //pixelated when stretching
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST); //pixelated when shrinking

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image = stbi_load(filepath, width, height, channels,0);

        if (image != null) {
            glTexImage2D(GL_TEXTURE_2D, 0,GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);

        } else {
            assert false: "Error: Could not load image";
        }

        stbi_image_free(image);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, texID);
    }

    public void unBind() {
        glBindTexture(GL_TEXTURE_2D,0);
    }

    public void init(String filepath) {

    }


}
