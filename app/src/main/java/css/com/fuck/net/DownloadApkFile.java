package css.com.fuck.net;

import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import css.com.fuck.utils.FileUtil;
import css.com.fuck.utils.VersionUtil;

public class DownloadApkFile extends AsyncTask<String, Integer, String> {
	
	private OnDownListener mListener;
	private boolean cancel = false;
	
	public DownloadApkFile() {
	}
	
    @Override
    protected String doInBackground(String... sUrl) {
    	String result = "0";
        try {
            URL url = new URL(sUrl[0]);
            URLConnection connection = url.openConnection();
            connection.connect();
            int fileLength = connection.getContentLength();
            // 下载文件
            File dirFile= FileUtil.createFileDir(FileUtil.DOWNLOAD_PATH);
			File file = new File(dirFile, VersionUtil.getAppName()+".apk");
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(file);

            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
            	if (cancel) {
            		break;
            	}
                total += count;
                publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
            
            if (total == fileLength) {
            	result = "1";
            }
        } catch (Throwable e) {
            Log.d(DownloadApkFile.class.getSimpleName(), "DownloadApkFile Error: " + e.toString());
        }
        
        return result;
    } 
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        if (mListener != null) {
			mListener.onProgress(progress[0]);
		}
    }
    
    @Override
    protected void onPostExecute(String result) {
    	if (cancel) {
    		return;
    	}
    	if (result.equals("1")) {
    		if (mListener != null) {
    			mListener.onResult();
    		}
    	} else {
    		if (mListener != null) {
    			mListener.onError();
    		}
    	}
    }
    
    public interface OnDownListener {
    	public void onProgress(int progress);
    	public void onResult();
    	public void onError();
    }

	public OnDownListener getListener() {
		return mListener;
	}

	public void setListener(OnDownListener listener) {
		this.mListener = listener;
	}

	public void cancel() {
		cancel = true;
	}
	
}
