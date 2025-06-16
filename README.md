# Trzy zadania obsługi strumieni wej/wyj w Javie

## Środowisko

### Aplikacje zostały zbudowane w środowisku Apache Maven 3.5.3 oraz Java 1.8:

```
mvn -version
```

```
Apache Maven 3.5.3 (3383c37e1f9e9b3bc3df5050c29c8aff9f295297; 2018-02-24T20:49:05+01:00)
Maven home: E:\apache-maven-3.5.3\bin\..
Java version: 1.8.0_131, vendor: Oracle Corporation
Java home: E:\java\jdk1.8.0_131\jre
Default locale: pl_PL, platform encoding: Cp1250
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

## Kompilowanie

### Będąc w głównym katalogu projektu, należy wykonać polecenie:

```
mvn -U clean compile
```

```
...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO]
[INFO] lsn_io_backend_tasks 0.0.1 ......................... SUCCESS [  5.323 s]
[INFO] lsn_io_backend_task1 ............................... SUCCESS [  4.791 s]
[INFO] lsn_io_backend_task2 ............................... SUCCESS [  0.142 s]
[INFO] lsn_io_backend_task3 0.0.1 ......................... SUCCESS [  0.145 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 10.611 s
[INFO] Finished at: 2025-06-16T08:41:13+02:00
[INFO] ------------------------------------------------------------------------
```

## Testowanie

### Będąc w głównym katalogu projektu, należy wykonać polecenie:

```
mvn -U clean test
```

```
...
[INFO] --- maven-surefire-plugin:3.2.5:test (default-test) @ lsn_io_backend_task3 ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running lsn.io.backend.Task3Test
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.060 s -- in lsn.io.backend.Task3Test
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
...
```

## Budowanie poszczególnych zadań

### Będąc w głównym katalogu projektu, należy wykonać polecenie:

```
mvn -U clean package
```

```
...
[INFO] --- maven-jar-plugin:3.2.0:jar (default-jar) @ lsn_io_backend_task1 ---
[INFO] Building jar: E:\pets_by_python\lsn_io_backend_tasks\lsn_io_backend_task1\deploy\lsn_io_backend_task1-0.0.1\lsn_io_backend_task1-0.0.1.jar
[INFO]
[INFO] --- maven-dependency-plugin:3.1.2:copy-dependencies (copy-runtime-dependencies) @ lsn_io_backend_task1 ---
...
[INFO] --- maven-jar-plugin:3.2.0:jar (default-jar) @ lsn_io_backend_task2 ---
[INFO] Building jar: E:\pets_by_python\lsn_io_backend_tasks\lsn_io_backend_task2\deploy\lsn_io_backend_task2-0.0.1\lsn_io_backend_task2-0.0.1.jar
[INFO]
[INFO] --- maven-dependency-plugin:3.1.2:copy-dependencies (copy-runtime-dependencies) @ lsn_io_backend_task2 ---
...
[INFO] --- maven-jar-plugin:3.2.0:jar (default-jar) @ lsn_io_backend_task3 ---
[INFO] Building jar: E:\pets_by_python\lsn_io_backend_tasks\lsn_io_backend_task3\deploy\lsn_io_backend_task3-0.0.1\lsn_io_backend_task3-0.0.1.jar
[INFO]
[INFO] --- maven-dependency-plugin:3.1.2:copy-dependencies (copy-runtime-dependencies) @ lsn_io_backend_task3 ---
...
```

## Uruchamianie zadania 1.

### Będąc w głównym katalogu projektu, należy przejść do katalogu zbydowanej aplikacji:

```
cd lsn_io_backend_task1\deploy\lsn_io_backend_task1-0.0.1
```

### Wykonać polecenie:

```
java -jar lsn_io_backend_task1-0.0.1.jar
```

### ... i wprowadzać na konsoli dane:

```
1 2 3 4 5 1 -1
-1 1 2 3 4 5
count: 7
distinct: 6
min: -1
max: 5
1
1
count: 1
distinct: 1
min: 1
max: 1
^Z
```

### lub wykonać polecenie:

```
more ..\..\src\test\resources\inputs\inputTask1.txt|java -jar lsn_io_backend_task1-0.0.1.jar
```

```
-7
count: 1
distinct: 1
min: -7
max: -7
0
count: 47
distinct: 1
min: 0
max: 0
-3 -2 -1 0 2 12
count: 7
distinct: 6
min: -3
max: 12
```

## Uruchamianie zadania 2.

### Będąc w głównym katalogu projektu, należy przejść do katalogu zbydowanej aplikacji:

```
cd lsn_io_backend_task2\deploy\lsn_io_backend_task2-0.0.1
```

### Wykonać polecenie:

```
java -jar lsn_io_backend_task2-0.0.1.jar
```

### ... i wprowadzać na konsoli dane:

```
1 2 10 7 5 3 6 6 13 0
0 13
0 0 0 0 0 0
0 13
3 10
6 7
6 7
0 13
1 12 10 3
1 12
3 10
^Z
```

### lub wykonać polecenie:

```
more ..\..\src\test\resources\inputs\inputTask2.txt|java -jar lsn_io_backend_task2-0.0.1.jar
```

```
0 13
3 10
6 7
6 7
0 13
1 12
3 10
```

## Uruchamianie zadania 3.

### Będąc w głównym katalogu projektu, należy przejść do katalogu zbydowanej aplikacji:

```
cd lsn_io_backend_task3\deploy\lsn_io_backend_task3-0.0.1
```

### Wykonać polecenie:

```
java -jar lsn_io_backend_task3-0.0.1.jar
```

### ... i wprowadzać na konsoli dane:

```
4
1 2
4 5
6 7
2 6
2
^Z
```

### lub wykonać polecenie:

```
more ..\..\src\test\resources\inputs\inputTask3.txt|java -jar lsn_io_backend_task3-0.0.1.jar
```

```
1
2
```
