# HammingErrorCorrectingCodeSimulator-With-Java-Swing

Hamming Code Simulator, kullanıcının girdiği veriyi Hamming kodu kullanarak kodlayan ve bellekte saklayan, hataları tanımlayan ve düzelten bir Java Swing uygulamasıdır.

## Özellikler

- *Veri Kodlama*: 4, 8 veya 16 bitlik giriş verisini Hamming koduyla kodlar.
- *Bellekte Saklama*: Kodlanmış veriyi bellekte saklar.
- *Hata Oluşturma*: Kodlanmış veride rastgele bir bit hatası oluşturur.
- *Hata Tespit ve Düzeltme*: Oluşturulan hatayı tespit eder ve düzeltir.
- *Grafik Arayüz*: Verilerin ve kodlamaların grafiksel olarak gösterimi.

## Kurulum ve Çalıştırma

1. *Depoyu Klonlayın*

    bash
    git clone https://github.com/Ah2m1et/HammingErrorCorrectingCodeSimulator-With-Java-Swing.git
    cd HammingErrorCorrectingCodeSimulator-With-Java-Swing
    

2. *Proje Dosyalarını Derleyin*

    bash
    javac HammingCodeSimulator.java
    

3. *Uygulamayı Çalıştırın*

    bash
    java HammingCodeSimulator
    

## Kullanım

1. *Veri Girişi*: "Input Data" alanına 4, 8 veya 16 bitlik binary veri girin.
2. *Kodlama ve Saklama*: "Encode & Save" butonuna tıklayarak veriyi kodlayın ve bellekte saklayın. Kodlanmış veri arayüzde gösterilecektir.
3. *Bellek Okuma*: "Read Memory" butonuna tıklayarak bellekte saklanan tüm kodlanmış verileri görüntüleyin.
4. *Hata Oluşturma*: "Induce Error" butonuna tıklayarak bellekteki bir kodlanmış veride rastgele bir bit hatası oluşturun ve hatayı tespit edin.

## Kod Açıklamaları

- *initializeUI*: Kullanıcı arayüzünü oluşturur.
- *displayBits*: Kodlanmış veriyi ve hata konumlarını grafiksel olarak gösterir.
- *EncodeButtonListener*: Kullanıcı veri girişi yapıp "Encode & Save" butonuna tıkladığında kodlama işlemini gerçekleştirir ve veriyi bellekte saklar.
- *ReadMemoryButtonListener*: Bellekteki tüm kodlanmış verileri listeler.
- *InduceErrorButtonListener*: Kodlanmış veride rastgele bir hata oluşturur, hatayı tespit eder ve düzeltir.
- *encodeHamming*: Veriyi Hamming kodu kullanarak kodlar.
- *correctHamming*: Kodlanmış verideki hatayı tespit eder ve düzeltir.

## Ekran Görüntüsü

![Ekran Görüntüsü](Ekran görüntüsü 2024-05-20 193906.png)

## Geliştirme

1. *Yeni Özellikler Ekleyin*: Uygulamaya yeni özellikler eklemek için HammingCodeSimulator sınıfını güncelleyebilirsiniz.
2. *Hataları Düzeltin*: Hataları bulup düzeltmek için pull request oluşturabilirsiniz.
3. *Kullanıcı Arayüzünü Geliştirin*: Arayüzü daha kullanıcı dostu hale getirmek için initializeUI metodunda değişiklikler yapabilirsiniz.

## Katkıda Bulunma

1. Depoyu forklayın.
2. Yeni bir dal (feature-branch) oluşturun.
3. Değişikliklerinizi yapın ve commit edin.
4. Dalınıza push edin (git push origin feature-branch).
5. Bir pull request oluşturun.

## Lisans

Bu proje MIT Lisansı ile lisanslanmıştır. Daha fazla bilgi için LICENSE dosyasına bakın.
