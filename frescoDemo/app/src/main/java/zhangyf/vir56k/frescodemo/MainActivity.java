package zhangyf.vir56k.frescodemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView my_image_view0;

    private SimpleDraweeView my_image_view1;
    private SimpleDraweeView my_image_view2;
    private SimpleDraweeView my_image_view3;
    private SimpleDraweeView my_image_viewRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);

        setContentView(R.layout.activity_main);

        my_image_view0 = (SimpleDraweeView) findViewById(R.id.my_image_view0);
        my_image_view1 = (SimpleDraweeView) findViewById(R.id.my_image_view1);
        my_image_view2 = (SimpleDraweeView) findViewById(R.id.my_image_view2);
        my_image_view3 = (SimpleDraweeView) findViewById(R.id.my_image_view3);
        my_image_viewRound = (SimpleDraweeView) findViewById(R.id.my_image_viewRound);


//        Uri uri = Uri.parse(str1);

        showImage_Round();
    }

    /**
     * 圆角
     */
    private void showImage_Round() {
        final String str2 = "http://img3.imgtn.bdimg.com/it/u=1003865389,4203869868&fm=21&gp=0.jpg";
        Uri uri = Uri.parse(str2);
        my_image_viewRound.setImageURI(uri);
    }

    /**
     * 简单加载
     */
    private void showSimpleImageUri() {
        final String strERR = "http://h.hiphotos.baidu.com/zhidao/pic/item/279759ee3d6d55fb65c51e786c224f4a20a4dd69_xxxxxxxxx.jpg";
        final String str2 = "http://h.hiphotos.baidu.com/zhidao/pic/item/279759ee3d6d55fb65c51e786c224f4a20a4dd69.jpg";
        Uri uri = Uri.parse(str2);
        Uri uriERR = Uri.parse(strERR);

        my_image_view0.setImageURI(uriERR);
    }

    /**
     * 一行显示三个，平均分配宽度。演示非 明确指定宽度的情形
     */
    private void showRowOfThree() {
        final String str1 = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        Uri uri = Uri.parse(str1);

        my_image_view1.setImageURI(uri);
        my_image_view2.setImageURI(uri);
        my_image_view3.setImageURI(uri);
    }


    //
    /**
     * 演示：逐渐加载的图片，即，从模糊逐渐清晰。需要图片本身也支持这种方式
     */
    private void showProgressiveJPEGs() {
        final String str3_progressive = "http://pooyak.com/p/progjpeg/jpegload.cgi?o=1";
        Uri uri = Uri.parse(str3_progressive);

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(my_image_view0.getController())
                .build();
        my_image_view0.setController(controller);
    }
}
