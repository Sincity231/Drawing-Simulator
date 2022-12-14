package components;

import imgui.ImGui;
import jade.Transform;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;

public class SpriteRenderer extends Component {
    private Vector4f color = new Vector4f(1, 1, 1, 1);
    private Sprite sprite = new Sprite();

    //transient variables are ignored during serialization via GSON
    private transient Transform lasTransform;
    private transient boolean isDirty = true;

//    public SpriteRenderer(Vector4f color){
//        this.color = color;
//        this.sprite = new Sprite(null);
//        this.isDirty = true;
//    }
//    public SpriteRenderer(Sprite sprite){
//        this.sprite = sprite;
//        this.color = new Vector4f(1, 1, 1, 1);
//        this.isDirty = true;
//    }

    @Override
    //accessing start method
    public void start(){
        this.lasTransform = gameObject.transform.copy();
    }
    @Override
    public void update(float dt) {
        if (!this.lasTransform.equals(this.gameObject.transform)){
            this.gameObject.transform.copy(this.lasTransform);
            isDirty = true;
        }
    }

    @Override
    public void imgui(){
        float[] imColor = {color.x, color.y, color.z, color.w};
        if(ImGui.colorPicker4("Color Picker: ", imColor)){
            this.color.set(imColor[0], imColor[1], imColor[2], imColor[3]);
            this.isDirty = true;
        }

    }
    public Vector4f getColor(){
        return this.color;
    }

    public Texture getTexture(){
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords(){
        return sprite.getTexCoords();
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
        this.isDirty = true;
    }

    public void setColor(Vector4f color){
        if (!this.color.equals(color)) {
            this.isDirty = true;
            this.color.set(color);
        }
    }

    public boolean isDirty(){
        return this.isDirty;
    }

    public void setClean(){
        this.isDirty = false;
    }

    public void setTexture(Texture texture){
        this.sprite.setTexture(texture);
    }
}