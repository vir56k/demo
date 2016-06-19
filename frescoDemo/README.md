# 概述 
 
    Fresco 是 facebook 的开源类库，它支持更有效的加载网络图片以及资源图片。它自带三级缓存功能，让图片显示更高效。
官方介绍

    Fresco 是一个强大的图片加载组件。
    Fresco 中设计有一个叫做 image pipeline 的模块。它负责从网络，从本地文件系统，本地资源加载图片。为了最大限度节省空间和CPU时间，它含有3级缓存设计（2级内存，1级文件）。
    Fresco 中设计有一个叫做 Drawees 模块，方便地显示loading图，当图片不再显示在屏幕上时，及时地释放内存和空间占用。
    Fresco 支持 Android2.3(API level 9) 及其以上系统。
# 简单使用 
简单来说，只需要三步。

        1. 添加依赖
        2. 编写布局
        3. 指定Uri
    
## 1.添加依赖  
在你的 模块级别 的gradle中写下：

    compile 'com.facebook.fresco:fresco:0.10.0'

## 2.编写布局  
声明命名空间 xmlns:fresco="http://schemas.android.com/apk/res-auto"

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:fresco="http://schemas.android.com/apk/res-auto">

写一个SimpleDraweeView

    <com.facebook.drawee.view.SimpleDraweeView android:id="@+id/my_image_view0"
    android:layout_width="200dp"
    android:layout_height="200dp"
    />

### 3.指定Uri  
    
    final String str2 = "http://h.hiphotos.baidu.com/zhidao/pic/item/279759ee3d6d55fb65c51e786c224f4a20a4dd69.jpg";
    Uri uri = Uri.parse(str2);
    my_image_view0.setImageURI(uriERR);
    
#Fresco的一些概念  
##DraweeView  

    继承于 View, 负责图片的显示。一般情况下，使用SimpleDraweeView 即可

##ImageRequest  

    ImageRequest存储着Image Pipeline处理被请求图片所需要的有用信息(Uri、是否渐进式图片、是否返回缩略图、缩放、是否自动旋转等)。

# Fresco的对 布局宽高的要求  

    你必须声明 android:layout_width 和 android:layout_height。
    如果没有在XML中声明这两个属性，将无法正确加载图像。

Drawees 不支持 wrap_content 属性。

这么做是有理由的：

    所下载的图像可能和占位图尺寸不一致，如果设置出错图或者重试图的话，这些图的尺寸也可能和所下载的图尺寸不一致。
    如果大小不一致，假设使用的是 wrap_content，图像下载完之后，View将会重新layout，改变大小和位置。这将会导致界面跳跃。

考虑到缓存的图片会根据你的尺寸进行缩略图，手机的屏幕会在旋转而导致imageview大小改变等，这些都会导致图片无法正常显示。

##固定宽高比  
只有希望显示固定的宽高比时，可以使用wrap_content。
如果希望图片以特定的宽高比例显示，例如 4:3，可以在XML中指定:

    <com.facebook.drawee.view.SimpleDraweeView
    android:id="@+id/my_image_view"
    android:layout_width="20dp"
    android:layout_height="wrap_content"
    fresco:viewAspectRatio="1.33"
    <!-- other attributes -->
也可以在代码中指定显示比例：

    mSimpleDraweeView.setAspectRatio(1.33f);
# 指定占位图片
使用 progressBarImage 指定 加载时显示的图片  
使用 failureImage 指定 加载失败的显示的图片  
使用 placeholderImage  指定占位图  

    <com.facebook.drawee.view.SimpleDraweeView
        android:id="@+id/my_image_view0"
        android:layout_width="200dp"
        android:layout_height="200dp"
        fresco:progressBarImage="@drawable/loading"
        fresco:progressBarImageScaleType="centerInside"
        fresco:failureImage="@drawable/error2"
        fresco:failureImageScaleType="centerInside"
        fresco:actualImageScaleType="centerCrop"
        fresco:placeholderImage="@drawable/loading" />

在加载失败时，可以设置点击重新加载。这时提供一个图片，加载失败时，会显示这个图片（而不是失败提示图片），提示用户点击重试。
在ControllerBuilder 中如下设置:

    .setTapToRetryEnabled(true)

# 指定加载失败图片和点击重新加载
在xml中指定加载失败后提示重试的图片

    fresco:retryImage="@drawable/retrying"
    fresco:retryImageScaleType="centerCrop"

并在ControllerBuilder 中如下设置:

    .setTapToRetryEnabled(true)
加载失败时，image pipeline 会重试四次；如果还是加载失败，则显示加载失败提示图片。

# 圆角图片
实现一个圆角图片是这么的容易，仅仅在xml布局里声明开启圆角，并指定 radius 即可。支持对四个角任意组合的圆角。

    <com.facebook.drawee.view.SimpleDraweeView
        android:id="@+id/my_image_viewRound"
        android:layout_width="200dp"
        android:layout_height="200dp"
        fresco:roundedCornerRadius="30dp"
        fresco:roundTopLeft="true"
        fresco:roundTopRight="false"
        fresco:roundBottomLeft="false"
        fresco:roundBottomRight="true"
        fresco:placeholderImage="@drawable/loading" />

# 渐进式JPEG图
Fresco 支持渐进式的网络JPEG图。在开始加载之后，图会从模糊到清晰渐渐呈现。
你可以设置一个清晰度标准，在未达到这个清晰度之前，会一直显示占位图。
渐进式JPEG图仅仅支持网络图

获得SimpleDraweeView
        my_image_view0 = (SimpleDraweeView) findViewById(R.id.my_image_view0);
构建ImageRequest加载图片

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

# 动画图支持  
Fresco 支持 GIF 和 WebP 格式的动画图片。对于 WebP 格式的动画图的支持包括扩展的 WebP 格式，即使 Android 2.3及其以后那些没有原生 WebP 支持的系统。
## 设置动画图自动播放
如果你希望图片下载完之后自动播放，同时，当View从屏幕移除时，停止播放，只需要在 image request 中简单设置，如下:

    Uri uri;
    DraweeController controller = Fresco.newDraweeControllerBuilder()
        .setUri(uri)
        .setAutoPlayAnimations(true)
        .build();
    mSimpleDraweeView.setController(controller);

# 监听下载事件  
有时候我们需要监听图片显示的过程，比如在失败，中间过程，成功时做一些事情。我们可以这么做：
1. 为 SimpleDraweeView 指定一个 DraweeController  
2. 为 DraweeController 指定一个 ControllerListener  
3. 在 ControllerListener 的回调方法里处理 失败，中间过程，成功时的事情

    Uri uri;
    DraweeController controller = Fresco.newControllerBuilder()
        .setControllerListener(controllerListener)
        .setUri(uri);
        .build();
    mSimpleDraweeView.setController(controller);

上面的代码指定了一个 ControllerListener ，它包含一些回调方法： 

    onFinalImageSet         加载完成
    onIntermediateImageSet  加载中间过程
    onFailure               加载失败

Fresco 提供了一个 推荐继承BaseControllerListener ，继承自 推荐继承BaseControllerListener 更方便，示例代码：

    ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
    @Override
    public void onFinalImageSet(
        String id,
        @Nullable ImageInfo imageInfo,
        @Nullable Animatable anim) {
      if (imageInfo == null) {
        return;
      }
      QualityInfo qualityInfo = imageInfo.getQualityInfo();
      FLog.d("Final image received! " + 
          "Size %d x %d",
          "Quality level %d, good enough: %s, full quality: %s",
          imageInfo.getWidth(),
          imageInfo.getHeight(),
          qualityInfo.getQuality(),
          qualityInfo.isOfGoodEnoughQuality(),
          qualityInfo.isOfFullQuality());
    }

    @Override 
    public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
      FLog.d("Intermediate image received");
    }

    @Override
    public void onFailure(String id, Throwable throwable) {
      FLog.e(getClass(), throwable, "Error loading %s", id)
    }
    };


# Fresco对各种Uri类型的资源的支持
Fresco使用 Uri 对象指定要显示的图片  
 res 示例:  
 
    Uri uri = Uri.parse("res://包名(实际可以是任何字符串甚至留空)/" + R.drawable.ic

 Fresco 支持许多URI格式。见下表：  
 
    类型	                  Scheme	               示例
    远程图片:	             http://, https://	    HttpURLConnection 或者参考 使用其他网络加载方案
    本地文件:  	           file://	              FileInputStream
    Content provider:    	content://	          ContentResolver
    asset目录下的资源:     	asset://	            AssetManager
    res目录下的资源:       	res://	              Resources.openRawResource

 特别注意：Fresco 不支持 相对路径的URI. 所有的URI都必须是绝对路径，并且带上该URI的scheme。  
#扩展资源  

    Github:  https://github.com/facebook/fresco  
    中文文档： http://www.fresco-cn.org/
