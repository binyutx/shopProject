package com.taotao.manager.utils; /**
 * 〈一句话功能简述〉<br>
 * 〈图片返回时需要的json格式的实体bean〉
 *
 * @author kepad
 * @create 2018/2/5
 * @since 1.0.0
 */

/**
 * @创建人 kepade
 * @创建时间 2018/2/5
 * @描述
 */

public class PicUploadResult {
    private Integer error;//0上传成功 1上传失败
    private String width;// 图片的宽
    private String height;// 图片的高
    private String url;// 图片上传的地址

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
