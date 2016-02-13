package com.example.useenator.androidHandlerexample;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity /* implements AdapterView.OnItemClickListener*/ {
    //    EditText mEditText;
//    ListView mListView;
//    String[] listOfImages;
    ProgressBar mProgressBar;
//    LinearLayout mLoadingSection = null;

    Thread mThread;
    public static Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mEditText = (EditText) findViewById(R.id.editText);
//        mListView = (ListView) findViewById(R.id.url_listview);
//        mListView.setOnItemClickListener(this);
//        listOfImages = getResources().getStringArray(R.array.list_url);
        mProgressBar = (ProgressBar) findViewById(R.id.download_progressBar);
//        mLoadingSection= (LinearLayout) findViewById(R.id.loading_section);
        mThread = new Thread(new MyRunnable());
        mThread.start();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mProgressBar.setProgress(msg.arg1);
            }
        };

//        mHandler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                mProgressBar.setProgress(msg.arg1);
//                return false;
//            }
//        });

    }

//    public void downloadImage(View view) {
//        String url=mEditText.getText().toString();
//        Thread myThread=new Thread(new DownloadImageThread(url));
//        myThread.start();
//
////        File file = Environment
////                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
////        Log.d("DIRECTORY_PICTURES", file.getAbsolutePath());
////
////        String url = listOfImages[0];
////        Uri uri = Uri.parse(url);
////        Log.d("uri LastPathSegment", uri.getLastPathSegment());
//    }
//
//    public boolean downloadImageUsingThreads(String url) {
//        boolean successful = false;
//        URL downloadURL = null;
//        HttpURLConnection httpURLConnection = null;
//        InputStream inputStream = null;
//        FileOutputStream fileOutputStream=null;
//        File file=null;
//
//        try {
//            URL
//            downloadURL = new URL(url);
//            HttpURLConnection //google recommand HttpURLConnection;not HttpClient
//            httpURLConnection = (HttpURLConnection) downloadURL.openConnection();
//            InputStream
//            inputStream = httpURLConnection.getInputStream();
////            FileOutputStream fileOutputStream;
//
//            String path=Environment
//                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//            .getAbsolutePath()+"/"+Uri.parse(url).getLastPathSegment();
//
//            file=new File(path);Log.d("uri LastPathSegment", file.getAbsolutePath() );
//            fileOutputStream=new FileOutputStream(file);
//            int read = -1;
//            byte[] buffer = new byte[1024];
//            while ((read = inputStream.read(buffer)) != -1) {//keep reading while read!=-1
//                fileOutputStream.write(buffer, 0, read);//(buffer,start,{read/length})
//                //Log.d("http", read + "");
//            }
//            successful=true;
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            ////////////////// set mLoadingSection INVISIBLE /////////////////
//            MainActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    mLoadingSection.setVisibility(View.INVISIBLE);
//                }
//            });
//            ////////////////// show loadin progress bar ////////////////////
//            if (httpURLConnection != null) {
//                httpURLConnection.disconnect();//disconnect to save resources
//            }
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                }
//            }
//            if (fileOutputStream != null) {
//                try {
//                    fileOutputStream.close();
//                } catch (IOException e) {
//                }
//            }
//
//        }
//        return successful;
//    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        mEditText.setText(listOfImages[position]);
//    }

    /***
     * Async task is match better then thread in android ;match simpler and most adapted:
     */
    private class MyRunnable implements Runnable {
//        public String url;
//
//        public DownloadImageThread(String url) {
//            this.url = url;
//        }/*extends Thread{*/

        @Override
        public void run() {

            for (int i = 0; i < 100; i++) {
                Message message = Message.obtain();
                message.arg1 = i;
                mHandler.sendMessage(message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
//            ////////////////// set mLoadingSection VISIBLE /////////////////
//            MainActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    mLoadingSection.setVisibility(View.VISIBLE);
//                }
//            });
//            ////////////////// show loadin progress bar //////////////////
//
//            downloadImageUsingThreads(url/*listOfImages[0]*/);
        }

    }
}
