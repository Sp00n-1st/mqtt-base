# MQTT BASE
## Cara Running Aplikasi

1. Clone Project Ini
2. Nyalakan VPN CloudFlare
3. Buat Rules Baru Di Firewall Untuk Port 1883 Agar Lalu Lintas Untuk Port 1883 Menjadi Lancar
4. Buka Project Ini Di IDE (Integrated Development Environment) 
5. Buka Terminal
6. Input Command mvn install
7. Input Command docker compose build
8. Input Command docker compose up
9. Berikut Jika Sudah Berhasil Build

![2024-07-31](https://github.com/user-attachments/assets/d90bfce3-25de-4fce-92c6-675719b53283)

## Cara Testing

1. Buka Terminal 
2. Input Command docker exec -it mqtt-base-mosquitto-1 mosquitto_sub -h localhost -t "test/rey79" atau docker exec -it <nama-container> mosquitto_sub -h localhost -t "test/rey79"
3. Hit Api
