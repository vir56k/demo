package eureka_client.demo.utils;

import ch.qos.logback.core.rolling.RolloverFailure;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    private static final String TAG = "FileUtil";

    /**
     * 获取文件扩展名
     * 无 dot
     *
     * @param fileName
     * @return
     */
    public static String getExtensionName(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;
    }

    /**
     * 获取文件扩展名
     * 有 dot
     *
     * @param fileName
     * @return
     */
    public static String getExtensionNameWithDot(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        return suffix;
    }

    public static URL fileToURL(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException var2) {
            throw new RuntimeException("Unexpected exception on file [" + file + "]", var2);
        }
    }

    public static boolean createMissingParentDirectories(File file) {
        File parent = file.getParentFile();
        if (parent == null) {
            return true;
        } else {
            parent.mkdirs();
            return parent.exists();
        }
    }

    public static String resourceAsString(ClassLoader classLoader, String resourceName) {
        URL url = classLoader.getResource(resourceName);
        if (url == null) {
            LogUtil.e(TAG, "Failed to find resource [" + resourceName + "]");
            return null;
        } else {
            InputStreamReader isr = null;

            try {
                URLConnection urlConnection = url.openConnection();
                urlConnection.setUseCaches(false);
                isr = new InputStreamReader(urlConnection.getInputStream());
                char[] buf = new char[128];
                StringBuilder builder = new StringBuilder();
                boolean var8 = true;

                int count;
                while ((count = isr.read(buf, 0, buf.length)) != -1) {
                    builder.append(buf, 0, count);
                }

                String var9 = builder.toString();
                return var9;
            } catch (IOException var19) {
                LogUtil.e("Failed to open " + resourceName, var19);
            } finally {
                if (isr != null) {
                    try {
                        isr.close();
                    } catch (IOException var18) {
                    }
                }

            }

            return null;
        }
    }

    public static void move(String src, String destination) throws RolloverFailure {
        copy(src, destination);
        File f = new File(src);
        if (!f.delete()) {
            String err = "删除src文件失败";
            LogUtil.e(TAG, err);
            throw new RolloverFailure(err);
        }
    }

    public static void copy(String src, String destination) throws RolloverFailure {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(src));
            bos = new BufferedOutputStream(new FileOutputStream(destination));
            byte[] inbuf = new byte['耀'];

            int n;
            while ((n = bis.read(inbuf)) != -1) {
                bos.write(inbuf, 0, n);
            }

            bis.close();
            bis = null;
            bos.close();
            bos = null;
        } catch (IOException var17) {
            String msg = "Failed to copy [" + src + "] to [" + destination + "]";
            LogUtil.e(msg, var17);
            throw new RolloverFailure(msg);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException var16) {
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException var15) {
                }
            }

        }
    }

    public static String combine(String path1, String... path2) {
        if (TextUtils.isEmpty(path1)) {
            throw new IllegalArgumentException("path1 is empty");
        }
        if (path2 == null || path2.length == 0) {
            return path1;
        }

        StringBuilder sb = new StringBuilder();
        if (path1.endsWith("/"))
            sb.append(path1.substring(0, path1.length() - 1));
        else
            sb.append(path1);
        for (String str : path2) {
            if (str == null || str.equals("")) continue;
            if (str.startsWith("/"))
                sb.append(str);
            else
                sb.append("/").append(str);
        }
        return sb.toString();
    }

    public static String readAllText(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 方式二
     * 该方式支持本地文件，也支持http/https远程文件
     *
     * @param fileUrl
     */
    public static String readContentType(String fileUrl) {
        String contentType = null;
        try {
            contentType = new MimetypesFileTypeMap().getContentType(new File(fileUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("getContentType, File ContentType is : " + contentType);
        return contentType;
    }

    public static void makeDir(String path) {
        File dir1 = new File(path);
        if (!dir1.exists()) {
            LogUtil.d(TAG, "## 准备创建dir: " + path);
            boolean mkdir = dir1.mkdir();
            if (!mkdir) {
                throw new RuntimeException("创建文件夹失败" + dir1);
            } else {
                LogUtil.d(TAG, "## 完成创建dir: " + path);
            }
        }
    }

    public static String getFileName(String newFile) {
        if (newFile == null) return newFile;
        if (!newFile.contains("/")) return newFile;
        String n = newFile.substring(newFile.lastIndexOf("/") + 1);
        return n;
    }
}
