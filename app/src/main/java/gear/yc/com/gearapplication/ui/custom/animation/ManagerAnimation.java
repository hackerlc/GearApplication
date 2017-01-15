package gear.yc.com.gearapplication.ui.custom.animation;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

/**
 * GearApplication
 * Created by YichenZ on 2016/8/12 14:47.
 */

public final class ManagerAnimation {
    private Context mContext;
    private AnimationSet mAnimationSet;

    public static Builder builder() {
        return new Builder();
    }

    public ManagerAnimation(Builder builder) {
        mContext=builder.mContext;
        mAnimationSet=builder.mAnimationSet;
    }

    public void inject(View view){
        view.setAnimation(mAnimationSet);
    }

    public AnimationSet getAnimationSet() {
        return mAnimationSet;
    }

    public static class Builder {
        private Context mContext;
        private AnimationSet mAnimationSet;
        private TranslateAnimation mTranslateAnimation;
        private AlphaAnimation mAlphaAnimation;

        private int translateDuration=1000;
        private int alphaDuration=1000;

        public Builder(){
            mAnimationSet=new AnimationSet(true);
        }

        public Builder setContext(Context context) {
            mContext = context;
            return this;
        }

        public Builder setTranslateAnimation(TranslateAnimation translateAnimation) {
            mTranslateAnimation = translateAnimation;

            return this;
        }

        public Builder setAlphaAnimation(AlphaAnimation alphaAnimation) {
            mAlphaAnimation = alphaAnimation;

            return this;
        }

        public Builder setTranslateDuration(int translateDuration) {
            this.translateDuration = translateDuration;
            return this;
        }

        public Builder setAlphaDuration(int alphaDuration) {
            this.alphaDuration = alphaDuration;
            return this;
        }

        public ManagerAnimation build() {
            if(mTranslateAnimation!=null){
                mTranslateAnimation.setDuration(translateDuration);
                mAnimationSet.addAnimation(mTranslateAnimation);
            }
            if(mAlphaAnimation!=null){
                mAlphaAnimation.setDuration(alphaDuration);
                mAnimationSet.addAnimation(mAlphaAnimation);
            }
            return new ManagerAnimation(this);
        }
    }
}
