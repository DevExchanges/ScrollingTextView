package info.devexchanges.scrollingtextview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TickerView tickerView, tickerUSD, tickerText;
    private char[] alphabetlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tickerView = (TickerView) findViewById(R.id.ticker_number);
        tickerUSD = (TickerView) findViewById(R.id.ticker_usd);
        tickerText = (TickerView) findViewById(R.id.ticker_text);

        alphabetlist = new char[53];
        alphabetlist[0] = TickerUtils.EMPTY_CHAR;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 26; j++) {
                // Add all lowercase characters first, then add the uppercase characters.
                alphabetlist[1 + i * 26 + j] = (char) ((i == 0) ? j + 97 : j + 65);
            }
        }

        tickerView.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerUSD.setCharacterList(TickerUtils.getDefaultListForUSCurrency());
        tickerText.setCharacterList(alphabetlist);

        //Default data
        tickerView.setText("2000");
        tickerUSD.setText("$5,200");
        tickerText.setText("ABcDef");
        setRandomText();
        setRandomCurrency();
    }

    public void setRandomText() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Random r = new Random();
                        int low = 1000;
                        int high = 10000;
                        final int result = r.nextInt(high - low) + low;
                        tickerView.setText("" + result);
                        handler.postDelayed(this, 1500);
                    }
                });
            }
        }, 2000);
    }

    public void setRandomCurrency() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Random r = new Random();
                        int low = 1000;
                        int high = 10000;
                        final int result = r.nextInt(high - low) + low;
                        NumberFormat formatter = new DecimalFormat("#,###,###");
                        String usdString = formatter.format(Integer.parseInt(String.valueOf(result)));
                        tickerUSD.setText("$" + usdString);
                        tickerText.setText(generateChars(r, alphabetlist, 6));

                        handler.postDelayed(this, 1500);
                    }
                });
            }
        }, 2000);
    }

    private String generateChars(Random random, char[] list, int numDigits) {
        final char[] result = new char[numDigits];
        for (int i = 0; i < numDigits; i++) {
            result[i] = list[random.nextInt(list.length)];
        }
        return new String(result);
    }
}