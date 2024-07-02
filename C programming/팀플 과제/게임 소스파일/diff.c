#include "common.h"

void diff(char *w)
{

	system("cls");
	gotoxy(55, 4);
	printf("<난이도>");
	Sleep(500);
	gotoxy(55, 8);
	printf("<1.초급>");
	gotoxy(55, 12);
	printf("<2.중급>");
	gotoxy(55, 16);
	printf("<3.상급>");
	gotoxy(68, 8);
	printf("．　　　　 　n__r 、\n");
	gotoxy(68, 9);
	printf("　　　　　 /). ェ.)/)\n");
	gotoxy(68, 10);
	printf("　　　 ／　　￣￣　＼\n");
	gotoxy(68, 11);
	printf("　　／　　　　　　　　 、\n");
	gotoxy(68, 12);
	printf("　/　　　　●　　　　　● 、\n");
	gotoxy(68, 13);
	printf("　!　　　　　　 　　 ▼　l  \n");
	gotoxy(68, 14);
	printf("　 、 　　　 　　 人 　ノ\n");
	gotoxy(68, 15);
	printf("　　　ㅁㅁㅁ―Ｊ――――Ｊ\n");
	Sleep(500);
	gotoxy(42, 20);
	printf("<선택할 난이도의 번호를 입력하세요>");
	gotoxy(58, 22);
	*w = getchar();
	while ((getchar() != '\n'));

}