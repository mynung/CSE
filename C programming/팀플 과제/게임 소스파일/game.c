#include "common.h"


void game(int * s)
{
	int s_time, j = 0;
	char word[SIZE], input[SIZE], ch;
	srand((unsigned int)time(0));
	t = 0;
	show_string(word, &s_time, *s);
	

	while (char_heart > 0 && boss_heart > 0)
	{
		if (time(0) >= s_time + timeout) { //타임아웃 체크
			t++;
			gotoxy(50, 10);
			printf("시간이 경과되었습니다!!");
			char_damage();
			Sleep(1000);
			char_heart--;
			show_string(word, &s_time, *s);
			j = 0;
			t--;
		}
		if (_kbhit() == 1) { //키 입력이 들어왔을때 
			gotoxy(67 + j, 27);
			ch = _getch();
			if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) //알파벳 소문자 또는 대문자 입력했을때 확인
				if (j < size - 1) {
					printf("%c", ch);
					input[j] = ch;
					j++;
				}

			if (ch == '\b') //벡스페이스 입력되었을 때
				if (j > 0) {
					printf("\b \b");
					input[j] = 0;
					j--;
				}
			if (ch == 13) { //엔터키를 입력하였을 때
				input[j] = '\0';
				if (strcmp(word, input) == 0) {
					t++;
					gotoxy(50, 10);
					printf("정답입니다! 공격!!");
					*s += (STD_TIME - (time(0) - s_time)) * 100 * lev;
					boss_heart--;
					bomb(14);
					boss_damage();
					Sleep(1000);
				}
				else {
					t++;
					gotoxy(50, 10);
					printf("뜨헉 오답입니다ㅠㅠ");
					char_heart--;
					char_damage();
					Sleep(1000);
				}
				show_string(word, &s_time, *s);
				t--;
				j = 0;
			}
		}

	}
	t = 1;
	system("cls");
}
