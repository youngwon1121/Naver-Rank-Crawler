# Naver-Rank-cralwer



## 소개

과거 회사에서 했던 프로젝트의 일부분을 리팩토링한 토이프로젝트입니다. (Spring boot + JPA)

특정 검색어에서 회사에서 관리하는 블로그가 상위에 노출되는지 확인하기 위해 크롤링을 합니다. 

아래와 같다면 블로그가 3등에 랭크된것입니다.

![example](https://user-images.githubusercontent.com/30296115/212557693-05f955e5-055d-4ca2-9521-2f75f0a06aa9.png)



--------------

## 기존의 문제점

각 검색어마다 3페이지씩 확인해야합니다. (30등씩 보여지므로 90등까지 확인, 그 외에는 확인필요X)

기존의 python코드는 multiprocess Pool를 통해 검색어마다 프로세스를 5개씩 생성했습니다.

이때 검색해야하는 갯수가 많아질수록 병렬처리 속도가 떨어지는 것이 체감이 되었습니다.

해당 원인을 https://github.com/brendangregg/FlameGraph tool를 통해 확인해봤습니다.

프로세스 생성(fork()) 수가 많은것도있지만 swapper의 call이 많은 것을 확인할 수 있었습니다.

swapper는 memory에 공간이 부족할 경우 데이터를 디스크로 swap-out 시켜주는 역할을합니다.

process의 생성이 많아져 메모리를 많이 차지할테니 call이 많을수밖에 없습니다.

![multiprocess](https://user-images.githubusercontent.com/30296115/212557703-cbe069c5-5f0f-4ecb-83cd-5bc82ec62e1b.png)









----------------

## 개선

기존의 코드를 Java로 옮기면서 위의 문제를 해결하고자 비동기를 위한 MultiProcessing을 MultiThreading으로 변경하고,

ThreadPool을 통해 스레드의 갯수를 제한했습니다.

언어간의 차이가 있을수도 있지만 300페이지를 확인하는데 기존 30초 -> 10초정도로 크게 감소했습니다.

-----------

## Diagram

Cralwer 패키지의 구성 Class Diagram입니다.

![diagram](https://user-images.githubusercontent.com/30296115/212557699-5ba70ab0-7bf6-4685-b25d-3c508275dedb.png)



