jug-camel-demo
==============

Aplikasi Demo untuk JUG Indonesia menggunakan framework apache camel.  

Presentasi
-------------
https://docs.google.com/presentation/d/1qASYWgLwxz1vu4ys0os7ZGN8GdY4Z_nBKCuOuSoaYAY/edit?usp=sharing

Catatan
-------------
0. Untuk menjalankan applikasi ini diharapkan pengguna telah terlebih dahulu melakukan configurasi maven.
0. Sebelum menjalankan applikasi harap menyalakan database terlebih dahulu.
0. Membuat schema baru pada database dengan nama ```jug_camel_demo```(untuk lebih jelasnya dapat dilihat pada ```src/main/resources/persistence.xml```)
0. Menyalakan ActiveMQ atau JMS Server

Informasi
-------------
* untuk menjalankan applikasi : ```mvn clean camel:run```

* file data terdapat pada directory : ```src/data/```

* directory untuk melakukan testing : ```rekap```

* hasil dari output process berada pada directory : ```target/result```
