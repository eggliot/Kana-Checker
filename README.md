# Kana Checker
A simple tool to check if you correctly translated Japanses Hiragana to Romanji or vice versa.

## Description
Provided a comma delimited string of Japanese Hiragana and Romanji, the program will check if the translation is correct. <br>

```Main``` is used simply for test cases and is not needed to run the program as a whole. (Contains more valid examples.) <br>
```KanaCheck``` is the main class that is used to run the program.

### Valid Input 
#### Criteria
- The string must be comma delimited
- The string must contain **_only_** Hiragana and Romanji characters and commas; no spaces or other separators
- The Hiragana and Romanji are grouped separately (i.e. all Hiragana are together and all Romanji are together.)
  - Either can go first: Hiragana or Romanji
- The first Hiragana corresponds to the first Romanji (If not then the translation will be marked incorrect.)
- Capitalization does **_not_** matter
- Commas and whitespace at the beginnings and ends do **_not_** matter

#### Examples
 - ```So,N,Ta,I,No,Ra,wo,To,Ra,Ya,そ,ん,た,い,の,ら,を,と,ら,や```
 - ```そ,ん,た,い,の,ら,を,と,ら,や,So,N,Ta,I,No,Ra,wo,To,Ra,Ya,```


## Usage
1. Naviagte to the ```src``` directory 
2. Run ```java KanaCheck.java str1 str2``` where **str1** and **str2** are the strings you want to be checked.
    - If you do not provide a string to be checked when running the file you will be prompted to enter one during runtime.

## Examples
```java KanaCheck.java``` <br>
The user will be prompted during runtime to input a string to be checked.

```java KanaCheck.java So,N,Ta,I,No,Ra,wo,To,Ra,Ya,そ,ん,た,い,の,ら,を,と,ら,や,``` <br>
The program will not prompt the user for input and will check the provided string.

``` java KanaCheck.java So,N,Ta,I,No,そ,ん,た,い,の, そ,ん,た,い,の,,So,N,Ta,I,No,```<br>
The program will check the provided strings. Note the space separating the two strings to be checked.