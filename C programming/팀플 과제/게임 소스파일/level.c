#include "common.h"


void level()
{
	int score=0;
	switch (lev)
	{
	case 1:
		char_heart = 5;
		boss_heart = 5;
		size = 4;
		timeout = 9;

		gotoxy(52, 10);
		Sleep(1000);
		printf("<LEVEL 1: 초급>");
		Sleep(1500);
		system("cls");
		Sleep(1000);
		picture();
		gotoxy(59, 10);
		printf("3");
		Sleep(1000);
		gotoxy(59, 10);
		printf("2");
		Sleep(1000);
		gotoxy(59, 10);
		printf("1");
		Sleep(1000);
		game(&score);
		if (boss_heart != 0) {
			system("cls");
			gameover(score);
			break;
		}
		clear();
		lev++;
	case 2:
		char_heart = 5;
		boss_heart = 5;
		size = 7;
		timeout = 8;

		gotoxy(52, 10);
		Sleep(1000);
		printf("<LEVEL 2: 중급>");
		Sleep(1500);
		system("cls");
		Sleep(1000);
		picture();
		gotoxy(59, 10);
		printf("3");
		Sleep(1000);
		gotoxy(59, 10);
		printf("2");
		Sleep(1000);
		gotoxy(59, 10);
		printf("1");
		Sleep(1000);
		game(&score);
		if (boss_heart != 0) {
			system("cls");
			gameover(score);
			break;
		}
		clear();
		lev++;
	case 3:
		char_heart = 5;
		boss_heart = 5;
		size = 10;
		timeout = 7;

		gotoxy(52, 10);
		Sleep(1000);
		printf("<LEVEL 3: 상급>");
		Sleep(1500);
		system("cls");
		Sleep(1000);
		picture();
		gotoxy(59, 10);
		printf("3");
		Sleep(1000);
		gotoxy(59, 10);
		printf("2");
		Sleep(1000);
		gotoxy(59, 10);
		printf("1");
		Sleep(1000);
		game(&score);
		if (boss_heart != 0) {
			system("cls");
			gameover(score);
			break;
		}
		system("cls");
		finish(score);
		lev++;
		break;	
	}
}