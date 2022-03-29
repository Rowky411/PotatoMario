package simple;

import components.SpriteRenderer;
import org.lwjgl.system.CallbackI;
import renderer.Shader;
import renderer.Texture;

import static org.lwjgl.opengl.GL20.*;

public class LevelEditorScene extends Scene {

    private float[] vertexArray = {
            //postion                   //color                   //UV Coordinates
            100f,  0f,  0.0f,       1.0f, 0.0f, 0.0f, 1.0f,          1, 0,
            0f,   100f, 0.0f,       1.0f, 1.0f, 0.0f, 1.0f,          0, 1,
            100f, 100f, 0.0f,       1.0f, 0.0f, 1.0f, 1.0f,          1, 1,
            0f,    0f,  0.0f,       1.0f, 1.0f, 0.0f, 1.0f,          0, 0
    };

    private Texture testTexture;
    private Shader defaultShader;

    GameObject testObj;
    private boolean firstTime = false;

    public LevelEditorScene(){

    }

    public void init() {
        System.out.println("Creating Object");
        this.testObj = new GameObject("test Object");
        this.testObj.addComponent(new SpriteRenderer());
        this.addGameObjectToScene(this.testObj);

        defaultShader = new Shader("assets/shaders/default.glsl");
        defaultShader.compile();

        this.testTexture = new Texture("assets/images/images.png");

    }

    public void uptade(float dt) {
        defaultShader.use();

        defaultShader.uploadTexture("TEX_SAMPLER", 0);
        glActiveTexture(GL_TEXTURE0);
        testTexture.bind();



        if (!firstTime) {
            GameObject go = new GameObject("Test 2");

            
        }
        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }
    }


}
