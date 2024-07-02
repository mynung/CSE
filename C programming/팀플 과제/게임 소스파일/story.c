#include "common.h"

unsigned _stdcall story(void* arg)
{

	while (1)
	{
		system("cls");
		gotoxy(16, 4);
		printf("(스토리를 스킵하려면 아무 키나 누르세요)");
		Sleep(1000);
		if (s == 0)
		{
			gotoxy(16, 6);
			printf("어느 평화로운 한 마을이 있었습니다.\n");
			Sleep(2000);
		}
		if (s == 0)
		{
			gotoxy(16, 8);
			printf("대포와 총의 뛰어난 품질로 국가에 이름이 알려진 이 마을에는...\n");
			Sleep(2000);
		}
		if (s == 0)
		{
			gotoxy(16, 10);
			printf("유명한 무기 기술자의 아들인 건국이가 살고 있었습니다.\n");
			Sleep(2000);
		}
		if (s == 0)
		{
			gotoxy(16, 12);
			printf("건국이의 아버지와 동료들은 무기 대량생산 프로젝트가 끝나서 다 같이 여름휴가를 떠나게 됩니다.\n");
			Sleep(2500);
		}
		if (s == 0)
		{
			gotoxy(16, 14);
			printf("이때 건국이의 마을에 괴물이 침입했습니다!!\n");
			Sleep(2000);
		}
		if (s == 0)
		{
			gotoxy(16, 16);
			printf("마을에는 건국이와 여자들, 노인들, 아이들 밖에 없어서 건국이 혼자서 이 마을을 지킬 수 밖에 없었습니다.\n");
			Sleep(2500);
		}
		if (s == 0)
		{
			gotoxy(16, 18);
			printf("과연 건국이는 괴물을 물리치고 마을을 지켜낼 수 있을까요?\n");
			Sleep(2000);
		}
		if (s == 0)
		{
			gotoxy(16, 20);
			printf("<계속 하려면 아무 키나 누르세요>");
		}
		break;
	}
}