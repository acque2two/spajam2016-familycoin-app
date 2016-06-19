package rainbow_rider.kirin.spajam;

import android.view.animation.Animation;
import android.view.animation.Transformation;


public class AnimationMyDraw extends Animation{

    private MyDraw mydraw;

    // 中心座標
    private float centerX;
    private float centerY;

    // アニメーション角度
    private float oldPos[] = new float[2];
    private float newPos[] = new float[2];

    public AnimationMyDraw(MyDraw mydraw, float newx, float newy) {
        this.oldPos[0] = mydraw.movex;
        this.oldPos[1] = mydraw.movey;
        this.newPos[0] = newx;
        this.newPos[1] = newy;
        this.mydraw = mydraw;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float gox = oldPos[0] + ((newPos[0] - oldPos[0]) * interpolatedTime);
        float goy = oldPos[1] + ((newPos[1] - oldPos[1]) * interpolatedTime);
        mydraw.MoveDef(gox, goy);
    }

}
