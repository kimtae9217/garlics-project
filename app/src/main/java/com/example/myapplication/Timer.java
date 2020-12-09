package com.example.myapplication;

        import android.os.Build;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.SystemClock;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.Chronometer;
        import android.widget.ImageButton;
        import android.widget.ImageView;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.annotation.RequiresApi;
        import androidx.core.content.res.ResourcesCompat;
        import androidx.fragment.app.Fragment;
        import android.widget.TextView;
        import android.app.Activity;
        import android.bluetooth.BluetoothAdapter;
        import android.bluetooth.BluetoothDevice;
        import android.bluetooth.BluetoothSocket;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.widget.Toast;

        import androidx.appcompat.app.AlertDialog;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.ViewModelProvider;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.io.Reader;
        import java.nio.ByteBuffer;
        import java.nio.ByteOrder;
        import java.nio.IntBuffer;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Set;
        import java.util.UUID;

public class Timer extends Fragment {
    Chronometer chronometer;

    ArrayList arrayList;

    private SharedViewModel sharedViewModel;



    ImageButton btStart, btStop, btPause;
    ImageView maneul2;
    Animation run_maneul;
    private boolean isResume;
    Handler handler;
    long tMilliSec, tStart, tBuff, tUpadate = 0L;
    int sec, min, milliSec, Kcal;
    int sum = 0;
    float average = 0f;
    public String Out_put;
    public String K_cal;
    ViewGroup viewGroup;
    private static final int REQUEST_ENABLE_BT = 10; // 블루투스 활성화 상태
    private BluetoothAdapter bluetoothAdapter; // 블루투스 어댑터
    private Set<BluetoothDevice> devices; // 블루투스 디바이스 데이터 셋
    private BluetoothDevice bluetoothDevice; // 블루투스 디바이스
    private BluetoothSocket bluetoothSocket = null; // 블루투스 소켓
    private OutputStream outputStream = null; // 블루투스에 데이터를 출력하기 위한 출력 스트림
    private InputStream inputStream = null; // 블루투스에 데이터를 입력하기 위한 입력 스트림
    private Thread workerThread = null; // 문자열 수신에 사용되는 쓰레드
    private byte[] readBuffer; // 수신 된 문자열을 저장하기 위한 버퍼
    private int readBufferPosition; // 버퍼 내 문자 저장 위치
    private TextView textViewReceive; // 수신 된 데이터를 표시하기 위한 텍스트 뷰

    int Xm = 70; // 남성 심박
    int Xf = 75; //남성 입위 심박
    int Am = 80; // 여성 심박
    int Af = 85; // 여성 입위 심박
    int weight = 65;//체중
    int height = 175;//신장
    int age = 25;
    double MR = 635.17; //남성 평균 체임피던스
    int FR = 833; // 여성 평균 체임피던스
    int maleBaseKcal = 34; //c Not C 남자 기초대사량
    int FemaleBaseKcal = 32; //c Not C 여자 기초대사량
    double ten_six = 1000000;
    int maleBPM_2 = 150;
    int femaleBPM_2 = 160;

    double fM = (0.0005742*(height*height))-(2.666*(ten_six*(1/((MR-23.091)*(MR-23.091)))))+(0.0002688*(ten_six*((height*height)/((MR-23.091)*(MR-23.091)))))+(0.00369*MR)+(0.3*weight)-(0.09*age)+3.140794;
    double fF = (0.0004871*(height*height))-(2.286*(ten_six*(1/((FR-23.091)*(FR-23.091)))))+(0.0002250*(ten_six*((height*height)/((FR-23.091)*(FR-23.091)))))+(0.0054*FR)+(0.25*weight)-(0.068*age)+14.7057;

    double Mj = (weight-fM)/weight*100; // 체지방율 = (weight-fM or fF)/weight/100 - 남성
    double Fj = (weight-fF)/weight*100; // 체지방율 = (weight-fM or fF)/weight/100 - 남성

    double a = weight * 0.444 * height * 0.663 * 88.83;//몸의 표면 면적 a = W × 0.444 × H × 0.663 × 88.83

    double mC = maleBaseKcal * a / 60; /* 분당 기초 대사량 C = c × a / 60 -> 남성 */
    double fC = FemaleBaseKcal * a / 60; /* 분당 기초 대사량 C = c × a / 60 -> 여성 */

    double YM1 = 0.01808*(Xm-Am+20.25) + mC;//운동 전 남성 단위 시간당 칼로리 소비량 Ym1 (kcal/min) = 0.01808(Xm-Am+20.25) + C |-> Xm = 운동 전 심박수 Am = 입위 안정시 심박수
    double YF1 = 0.00895*(Xf-Af+20.25) + fC;//운동 전 여성 단위 시간당 칼로리 소비량 Yf1 (kcal/min) = 0.00895(Xf-Af+20.25) + C |-> Xf = 운동 전 심박수 Af = 입위 안정시 심박수


    double BM = 0.0109*(fM/(height*height)) - 0.0023 * Mj - 0.0007 * age - 0.0211; // BM = 0.0109×(fM/H^2) - 0.0023×Mj - 0.0007×A - 0.0211
    double BF = 0.0140*(fF/(height*height)) - 0.0012 * Fj - 0.1254; // BF = 0.0140×(fF/H^2) - 0.0012×Fj - 0.1254

    //double YM2 = BM*(BPM-age) + mC + 0.3645; // 운동 후 남성 단위 시간당 칼로리 소비량 = BM(X-A) + C + 0.3645
    double YF2 = BF*(femaleBPM_2-age) + fC + 0.1812; // 운동 후 여성 단위 시간당 칼로리 소비량 = BF(X-A) + C + 0.1812

    //double MK = 0.5*(BPM-Xm)*(YM2-YM1); // = 0.5*(maleBPM_2-Xm)*(YM2-YM1) // 운동으로 소비한 칼로리 최종 소비량 - 남성
    double FK = 0.5*(femaleBPM_2-Xf)*(YF2-YF1); // = 0.5*(femaleBPM_2-Xf)*(YF2-YF1) // 운동으로 소비한 칼로리 최종 소비량 - 여성

    int requestCode;
    int resultCode;
    int data;
    public BluetoothAdapter mBluetoothAdapter;
    public Set<BluetoothDevice> mDevices;
    private BluetoothSocket bSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private BluetoothDevice mRemoteDevice;
    public boolean onBT = false;
    public byte[] sendByte = new byte[4];


    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.timer, container, false);
        chronometer = (Chronometer) viewGroup.findViewById(R.id.chronometer);
        btStart = (ImageButton) viewGroup.findViewById(R.id.bt_start);
        btPause = (ImageButton) viewGroup.findViewById(R.id.bt_pause);
        btStop = (ImageButton) viewGroup.findViewById(R.id.bt_stop);
        maneul2 =(ImageView) viewGroup.findViewById(R.id.timer_maneul2);
        textViewReceive = (TextView) viewGroup.findViewById(R.id.textView_receive);

        run_maneul = AnimationUtils.loadAnimation(getActivity(), R.anim.run_maneul);
        handler = new Handler();

        arrayList = new ArrayList();

        sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);



        btStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                btPause.setVisibility(View.INVISIBLE);

                maneul2.startAnimation(run_maneul); //Animation Start

                if (!isResume) {
                    tStart = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    chronometer.start();
                    isResume = true;
                    btStop.setVisibility(View.GONE);
                    btPause.setVisibility(View.GONE);

                    btStart.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_pause, null));


                    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (bluetoothAdapter.isEnabled()) {
                        devices = bluetoothAdapter.getBondedDevices();
                        int pariedDeviceCount = devices.size();
                        if (pariedDeviceCount == 0) {
                            Toast.makeText(getContext(), "먼저 장치를 페어링 해주세요!",Toast.LENGTH_SHORT).show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("페어링 되어있는 블루투스 디바이스 목록");
                            List<String> list = new ArrayList<>();
                            for (BluetoothDevice bluetooteDevice : devices) {
                                list.add(bluetooteDevice.getName());
                            }
                            list.add("취소");
                            final CharSequence[] charSequences = list.toArray(new CharSequence[list.size()]);
                            list.toArray(new CharSequence[list.size()]);

                            builder.setItems(charSequences, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    connectDevice(charSequences[which].toString());
                                }
                            });
                            builder.setCancelable(false);
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }
                    else {
                        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(intent, REQUEST_ENABLE_BT);
                    }

                } else {
                    tBuff += tMilliSec;
                    handler.removeCallbacks(runnable);
                    chronometer.stop();
                    isResume = false;
                    btStop.setVisibility(View.VISIBLE);
                    btStart.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_play, null));
                    sharedViewModel.setLiveData(String.format("%02d", min) + ":" + String.format("%02d", sec) + ":" + String.format("%02d", milliSec));

                }
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                maneul2.clearAnimation(); //Animation Stop
                if (!isResume) {
                    btStart.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_play, null));
                    tMilliSec = 0L;
                    tStart = 0L;
                    tBuff = 0L;
                    tUpadate = 0L;
                    sec = 0;
                    min = 0;
                    milliSec = 0;
                    chronometer.setText("00:00:00");
                    receiveData(); //블루투스 데이터 받아오는 기능
                    K_cal = Out_put;
                    textViewReceive.setText(K_cal + "\n");

                }
            }
        });
        return viewGroup;
    }



    public void connectDevice(String deviceName) {
        for(BluetoothDevice tempDevice :devices) {
            if(deviceName.equals(tempDevice.getName())) {
                bluetoothDevice = tempDevice;
                break;
            }
        }
        UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        try {
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
            bluetoothSocket.connect();
            outputStream = bluetoothSocket.getOutputStream();
            inputStream = bluetoothSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tMilliSec = SystemClock.uptimeMillis() - tStart;
            tUpadate = tBuff + tMilliSec;
            sec = (int) (tUpadate / 1000);
            min = sec / 60;
            sec = sec % 60;
            milliSec = (int) (tUpadate % 100);
            chronometer.setText(String.format("%02d", min) + ":" + String.format("%02d", sec) + ":" + String.format("%02d", milliSec));
            handler.postDelayed(this, 60);
        }
    };

    public void receiveData() {
        final Handler handler = new Handler();
        readBufferPosition = 0;
        readBuffer = new byte[1024];

        workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!(Thread.currentThread().isInterrupted())) {
                    try {
                        int byteAvailable = inputStream.available();
                        if(byteAvailable > 0){
                            byte[] bytes = new byte[byteAvailable];
                            inputStream.read(bytes);
                            for(int i = 0; i < byteAvailable; i++) {
                                byte tempByte = bytes[i];
                                if(tempByte == '\n') {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    String text = new String(encodedBytes, "US-ASCII");
                                    /*IntBuffer intbuf = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
                                    int[] BPM = new int[intbuf.remaining()];
                                    intbuf.get(BPM);
                                    for(int j = 0; j>BPM.length; j++) {
                                        sum += BPM[j];
                                    }
                                    average = sum / BPM.length;*/ // Byte Array to int Array 잘 안되는 듯.
                                    readBufferPosition = 0;
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            //String str_BPM = new String(text);
                                            double BPM = Double.parseDouble(text);
                                            double YM2 = BM*(BPM-age) + mC + 0.3645; // 운동 후 남성 단위 시간당 칼로리 소비량 = BM(X-A) + C + 0.3645
                                            double MK = 0.5*(BPM-Xm)*(YM2-YM1); // = 0.5*(maleBPM_2-Xm)*(YM2-YM1) // 운동으로 소비한 칼로리 최종 소비량 - 남성
                                            //Kcal = (int) ((-55.969+(0.6309*140/*심박*/)+(0.1988*63/*몸무게*/)+(0.2017*25/*나이*/))/4.184*60*(0.016666*sec));
                                            Out_put = Double.toString(MK);
                                            //textViewReceive.setText(Out_put + "\n"); // 출력 함수
                                        }
                                    });
                                }
                                else {
                                    readBuffer[readBufferPosition++] = tempByte;
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        workerThread.start();
    }
}