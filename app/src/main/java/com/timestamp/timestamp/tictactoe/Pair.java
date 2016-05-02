package com.timestamp.timestamp.tictactoe;

/**
 * Created by Ryan on 5/1/2016.
 */
public class Pair {
        public Integer imgId;
        public Integer viewId;
        public Pair(){};
        public Pair(Integer imgId, Integer viewId) {
            this.imgId=imgId;
            this.viewId=viewId;
        }
        public void setValue(Integer imgId){
            this.imgId = imgId;
        }
        public void setId(Integer viewId){
            this.viewId = viewId;
        }
        public Integer getViewId(){
            return this.viewId;
        }
        public Integer getImgId(){
            return this.imgId;
        }
}
