#include "common.h"

void show_string(char* w, int* s_time, int s)
{
	int i;
	system("cls");
	*s_time = time(0);
	for (i = 0; i < size - 1; i++) {
		if ((rand() % 2) == 1)
			w[i] = 'a' + (rand() % 26);
		else
			w[i] = 'A' + (rand() % 26);
	}
	w[i] = '\0';
	picture();
	c_hp_bar(char_heart);
	b_hp_bar(boss_heart);
	gotoxy(1, 2);
	printf("SCORE: %d", s);
	gotoxy(49, 26);
	printf("==============================");
	gotoxy(49, 28);
	printf("==============================");
	gotoxy(49, 27);
	printf("문자를 입력하시오:");
	switch (lev)
	{
	case 1:	_beginthreadex(NULL, 0, (_beginthreadex_proc_type)boss_attack1, w, 0, NULL); break;
	case 2: _beginthreadex(NULL, 0, (_beginthreadex_proc_type)boss_attack2, w, 0, NULL); break;
	case 3: _beginthreadex(NULL, 0, (_beginthreadex_proc_type)boss_attack3, w, 0, NULL); break;
	}
}

void boss_attack1(char* W)
{
	gotoxy(18, 21);
	printf("%s", W);
	Sleep(1000);
	if (t == 0)
	{
		gotoxy(18, 21);
		printf("   ");
		gotoxy(27, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(27, 21);
		printf("   ");
		gotoxy(36, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(36, 21);
		printf("   ");
		gotoxy(45, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(45, 21);
		printf("   ");
		gotoxy(54, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(54, 21);
		printf("   ");
		gotoxy(63, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(63, 21);
		printf("   ");
		gotoxy(72, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(72, 21);
		printf("   ");
		gotoxy(81, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(81, 21);
		printf("   ");
		gotoxy(90, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(90, 21);
		printf("   ");
	}
}

void boss_attack2(char* W)
{
	gotoxy(18, 21);
	printf("%s", W);
	Sleep(1000);
	if (t == 0)
	{
		gotoxy(18, 21);
		printf("          ");
		gotoxy(28, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(28, 21);
		printf("          ");
		gotoxy(38, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(38, 21);
		printf("          ");
		gotoxy(48, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(48, 21);
		printf("          ");
		gotoxy(58, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(58, 21);
		printf("          ");
		gotoxy(68, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(68, 21);
		printf("          ");
		gotoxy(78, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(78, 21);
		printf("          ");
		gotoxy(88, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(88, 21);
		printf("          ");
	}
}

void boss_attack3(char* W)
{
	gotoxy(18, 21);
	printf("%s", W);
	Sleep(1000);
	if (t == 0)
	{
		gotoxy(18, 21);
		printf("          ");
		gotoxy(29, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(29, 21);
		printf("          ");
		gotoxy(40, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(40, 21);
		printf("          ");
		gotoxy(51, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(51, 21);
		printf("          ");
		gotoxy(62, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(62, 21);
		printf("          ");
		gotoxy(73, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(73, 21);
		printf("          ");
		gotoxy(84, 21);
		printf("%s", W);
		Sleep(1000);
	}
	if (t == 0)
	{
		gotoxy(84, 21);
		printf("          ");
	}

}
