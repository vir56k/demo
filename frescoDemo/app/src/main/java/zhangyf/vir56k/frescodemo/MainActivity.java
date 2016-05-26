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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);

        setContentView(R.layout.activity_main);

        SimpleDraweeView my_image_view1 = (SimpleDraweeView) findViewById(R.id.my_image_view1);
        SimpleDraweeView my_image_view2 = (SimpleDraweeView) findViewById(R.id.my_image_view2);
        SimpleDraweeView my_image_view3 = (SimpleDraweeView) findViewById(R.id.my_image_view3);

        String str1 = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        String str2 = "http://h.hiphotos.baidu.com/zhidao/pic/item/279759ee3d6d55fb65c51e786c224f4a20a4dd69.jpg";
        String str3_progressive = "http://pooyak.com/p/progjpeg/jpegload.cgi?o=1";

//        Uri uri = Uri.parse(str3_progressive);
        Uri uri = Uri.parse(str1);

        showImageUri(my_image_view1, uri);
        showImageUri(my_image_view2, uri);
        showImageUri(my_image_view3, uri);
//        showProgressiveJPEGs(mSimpleDraweeView, uri);
    }

    private void showImageUri(SimpleDraweeView simpleDraweeView, Uri uri) {
        simpleDraweeView.setImageURI(uri);
    }

    private void showProgressiveJPEGs(SimpleDraweeView simpleDraweeView, Uri uri) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }
}
