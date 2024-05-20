import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class HammingCodeSimulator {

    private JFrame frame;
    private JTextField inputText;
    private JPanel bitPanel;
    private DefaultListModel<String> memoryModel;
    private ArrayList<String> memoryList;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HammingCodeSimulator::new);
    }

    public HammingCodeSimulator() {
        memoryList = new ArrayList<>();  // Bellek listesini başlat
        initializeUI();  // Kullanıcı arayüzünü başlat
    }

    // Kullanıcı arayüzünü oluşturma
    private void initializeUI() {
        frame = new JFrame("Hamming Code Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 250));
        frame.add(panel);

        // Veri giriş etiketi ve metin kutusu
        JLabel inputLabel = new JLabel("Input Data:");
        inputLabel.setBounds(10, 20, 100, 25);
        inputLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(inputLabel);

        inputText = new JTextField(20);
        inputText.setBounds(120, 20, 200, 25);
        panel.add(inputText);

        // Encode ve save butonu
        JButton encodeButton = new JButton("Encode & Save");
        encodeButton.setBounds(330, 20, 150, 25);
        encodeButton.setBackground(new Color(135, 206, 250));
        encodeButton.setForeground(Color.WHITE);
        panel.add(encodeButton);

        // Bellek etiketi
        JLabel memoryLabel = new JLabel("Memory:");
        memoryLabel.setBounds(10, 60, 100, 25);
        memoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(memoryLabel);

        // Bellek listesi ve scroll panel
        memoryModel = new DefaultListModel<>();
        JList<String> memoryListDisplay = new JList<>(memoryModel);
        JScrollPane memoryScrollPane = new JScrollPane(memoryListDisplay);
        memoryScrollPane.setBounds(120, 60, 200, 150);
        panel.add(memoryScrollPane);

        // Bellekten oku butonu
        JButton readMemoryButton = new JButton("Read Memory");
        readMemoryButton.setBounds(330, 60, 150, 25);
        readMemoryButton.setBackground(new Color(255, 165, 0));
        readMemoryButton.setForeground(Color.WHITE);
        panel.add(readMemoryButton);

        // Hata oluştur butonu
        JButton induceErrorButton = new JButton("Induce Error");
        induceErrorButton.setBounds(330, 100, 150, 25);
        induceErrorButton.setBackground(new Color(255, 99, 71));
        induceErrorButton.setForeground(Color.WHITE);
        panel.add(induceErrorButton);

        // Hamming kodu etiketi ve bit paneli
        JLabel bitPanelLabel = new JLabel("Hamming Code:");
        bitPanelLabel.setBounds(10, 230, 150, 25);
        bitPanelLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(bitPanelLabel);

        bitPanel = new JPanel();
        bitPanel.setBounds(120, 230, 600, 200);
        bitPanel.setBackground(new Color(255, 248, 220));
        panel.add(bitPanel);

        // Butonlara tıklama olayları ekleniyor
        encodeButton.addActionListener(new EncodeButtonListener());
        readMemoryButton.addActionListener(new ReadMemoryButtonListener());
        induceErrorButton.addActionListener(new InduceErrorButtonListener());

        frame.setVisible(true);
    }

    // Bitleri ve renklerini gösteren metod
    private void displayBits(String data, int errorBit) {
        bitPanel.removeAll();
        bitPanel.setLayout(new GridLayout(1, data.length(), 5, 5));

        // Bitlerin renklenmesi sağdan sola olacak şekilde ayarlandı
        for (int i = data.length() - 1; i >= 0; i--) {
            JLabel bitLabel = new JLabel(String.valueOf(data.charAt(i)), SwingConstants.CENTER);
            bitLabel.setOpaque(true);
            bitLabel.setFont(new Font("Courier New", Font.BOLD, 20));
            if (Math.log(i + 1) / Math.log(2) % 1 == 0) {
                bitLabel.setBackground(new Color(135, 206, 250)); // Parity bit (açık mavi)
            } else {
                bitLabel.setBackground(new Color(144, 238, 144)); // Data bit (açık yeşil)
            }
            if (data.length() - 1 - i == errorBit) {
                bitLabel.setBackground(Color.RED); // Hatalı bit (kırmızı)
            }
            bitPanel.add(bitLabel);
        }
        bitPanel.revalidate();
        bitPanel.repaint();
    }

    // Encode ve save butonu tıklama olayı
    private class EncodeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputData = inputText.getText();
            if (inputData.length() != 4 && inputData.length() != 8 && inputData.length() != 16) {
                JOptionPane.showMessageDialog(frame, "Input data must be 4, 8, or 16 bits long.");
                return;
            }

            String encodedData = encodeHamming(inputData);
            if (encodedData != null) { // encodedData null değilse devam et
                memoryList.add(encodedData);
                memoryModel.addElement(encodedData);
                displayBits(encodedData, -1);
                JOptionPane.showMessageDialog(frame, "Data encoded and saved successfully.\nEncoded Data: " + encodedData);
            }
        }
    }

    // Bellekten oku butonu tıklama olayı
    private class ReadMemoryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder memoryContents = new StringBuilder("Memory Contents:\n");
            for (String data : memoryList) {
                memoryContents.append(data).append("\n");
            }
            JOptionPane.showMessageDialog(frame, memoryContents.toString());
        }
    }

    // Hata oluştur butonu tıklama olayı
    private class InduceErrorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (memoryList.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Memory is empty. Please encode and save data first.");
                return;
            }

            Random random = new Random();
            int selectedIndex = random.nextInt(memoryList.size());
            String selectedData = memoryList.get(selectedIndex);
            int errorBit = random.nextInt(selectedData.length());
            char[] dataWithError = selectedData.toCharArray();
            dataWithError[errorBit] = (dataWithError[errorBit] == '0') ? '1' : '0';

            String dataWithErrorStr = new String(dataWithError);
            String correctedData = correctHamming(dataWithErrorStr);

            displayBits(dataWithErrorStr, errorBit);
            JOptionPane.showMessageDialog(frame, "Selected Encoded Data: " + selectedData + "\n" +
                    "Data with Error (at bit " + (dataWithError.length - 1 - errorBit) + "): " + dataWithErrorStr + "\n" +
                    "Corrected Data: " + correctedData);
        }
    }

    // Hamming kodu hesaplama metodu
    private String encodeHamming(String data) {
        // Girilen verinin 0 ve 1 dışında karakter içerip içermediğini kontrol et
        if (!data.matches("[01]+")) {
            JOptionPane.showMessageDialog(frame, "Invalid input data. Please enter binary digits (0 or 1) only.");
            return null; // Geçersiz giriş durumunda null döndür
        }

        int r = 0;
        while (Math.pow(2, r) < data.length() + r + 1) {
            r++;
        }

        int[] hammingCode = new int[data.length() + r];
        int j = 0;
        for (int i = 1; i <= hammingCode.length; i++) {
            if (Math.pow(2, j) == i) {
                hammingCode[i - 1] = 0; // Initially parity bits are set to 0
                j++;
            } else {
                hammingCode[i - 1] = Character.getNumericValue(data.charAt(i - j - 1));
            }
        }

        // Parity bitlerin hesaplanması
        for (int i = 0; i < r; i++) {
            int position = (int) Math.pow(2, i);
            int value = 0;
            for (int k = 1; k <= hammingCode.length; k++) {
                if (((k >> i) & 1) == 1) {
                    value ^= hammingCode[k - 1];
                }
            }
            hammingCode[position - 1] = value;
        }

        StringBuilder encodedData = new StringBuilder();
        for (int bit : hammingCode) {
            encodedData.append(bit);
        }

        return encodedData.toString();
    }

    // Hamming kodu düzeltme metodu
    private String correctHamming(String data) {
        int r = 0;
        while (Math.pow(2, r) < data.length() + 1) {
            r++;
        }

        int[] hammingCode = new int[data.length()];
        for (int i = 0; i < data.length(); i++) {
            hammingCode[i] = Character.getNumericValue(data.charAt(i));
        }

        int errorPosition = 0;
        for (int i = 0; i < r; i++) {
            int position = (int) Math.pow(2, i);
            int value = 0;
            for (int k = 1; k <= hammingCode.length; k++) {
                if (((k >> i) & 1) == 1) {
                    value ^= hammingCode[k - 1];
                }
            }
            if (value != 0) {
                errorPosition += position;
            }
        }

        if (errorPosition != 0) {
            hammingCode[errorPosition - 1] ^= 1; // Hatalı biti düzelt
        }

        StringBuilder correctedData = new StringBuilder();
        for (int i = 0; i < hammingCode.length; i++) {
            if ((Math.log(i + 1) / Math.log(2)) % 1 != 0) {
                correctedData.append(hammingCode[i]);
            }
        }

        return correctedData.toString();
    }
}