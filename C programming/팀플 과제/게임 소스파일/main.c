#include "common.h"



int main()
{
	system("mode con cols=120 lines=30 | title KTB");
	char i, difficult;

	mainmenu();

	while (1)
	{
		lev = 0;
		s = 0;
		system("cls");
		gotoxy(45, 4);
		printf("*Kill The Boss 보스 죽이기*");
		Sleep(500);
		gotoxy(52, 8);
		printf("<1.시작하기>");
		gotoxy(52, 12);
		printf("<2.종료하기>");
		Sleep(500);
		gotoxy(20, 8);
		printf("．．．．．/)─―ヘ\n");
		gotoxy(20, 9);
		printf("　　　━／　　　  \\\\\n");
		gotoxy(20, 10);
		printf("　 ／　　　●　　● \\\n");
		gotoxy(16, 11);
		printf("．．．｜　　　　　　▼ |\n");
		gotoxy(20, 12);
		printf("　｜　　　　　 ㅗノ\n");
		gotoxy(12, 13);
		printf("．．．．． U￣U￣￣￣U￣U\n");
		gotoxy(45, 16);
		Sleep(500);
		printf("<선택할 번호를 입력하세요>");
		gotoxy(58, 18);
		i = getchar();
		while ((getchar() != '\n'));

		switch (i)
		{
		case '1':
			_beginthreadex(NULL, 0, story, 0, 0, NULL);
			_getch();
			s++;
			do
			{
				diff(&difficult);
				system("cls");
				switch (difficult)
				{
				case '1':
					lev = 1;
					attack_tab = 11;
					level();
					break;

				case '2':
					lev = 2;
					attack_tab = 13;
					level();
					break;
				case '3':
					lev = 3;
					attack_tab = 15;
					level();
					break;
				default:
					gotoxy(46, 18);
					printf("*올바른 번호가 아닙니다!*");
					Sleep(1500);
					gotoxy(46, 18);
					printf("                               ");
					gotoxy(58, 18);
					break;
				}
			} while (lev == 0);
			break;
		case '2':
			gotoxy(50, 18);
			printf("게임을 종료합니다.");
			Sleep(700);
			return 0;
			break;
		default:
			gotoxy(46, 18);
			printf("*올바른 번호가 아닙니다!*");
			Sleep(1500);
			gotoxy(46, 18);
			printf("                               ");
			gotoxy(58, 18);
			break;
			}
		}
	return 0;
}