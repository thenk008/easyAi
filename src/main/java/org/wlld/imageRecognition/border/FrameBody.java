package org.wlld.imageRecognition.border;

import org.wlld.MatrixTools.Matrix;

/**
 * @author lidapeng
 * @description 选区分类识别
 * @date 11:49 上午 2020/1/26
 */
public class FrameBody {
    private Matrix matrix;//图像区块矩阵
    private Matrix endMatrix;//卷积结束的矩阵
    private int id;//当前分类的ID
    private double point;//当前分类的概率
    private int x;//锚点X坐标
    private int y;//锚点Y坐标
    private double realX;//修正后的X坐标
    private double realY;//修正后的Y坐标
    private double realWidth;//修正后的宽度
    private double realHeight;//修正后的高度

    public double getRealX() {
        return realX;
    }

    public void setRealX(double realX) {
        this.realX = realX;
    }

    public double getRealY() {
        return realY;
    }

    public void setRealY(double realY) {
        this.realY = realY;
    }

    public double getRealWidth() {
        return realWidth;
    }

    public void setRealWidth(double realWidth) {
        this.realWidth = realWidth;
    }

    public double getRealHeight() {
        return realHeight;
    }

    public void setRealHeight(double realHeight) {
        this.realHeight = realHeight;
    }

    public Matrix getEndMatrix() {
        return endMatrix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
            point = 1;
        }
    }

    public double getPoint() {
        return point;
    }

    public void setPointAndId(double point, int id) {
        if (point > this.point) {
            this.point = point;
            this.id = id;
        }
    }

    public void setEndMatrix(Matrix endMatrix) {
        this.endMatrix = endMatrix;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
