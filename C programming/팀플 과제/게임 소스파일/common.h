#include <stdio.h>
#include <Windows.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <conio.h>
#include <process.h>
#define SIZE 50
#define STD_TIME 9

int char_heart;
int boss_heart;
int size;
int timeout;
int s;  //���丮 ��ŵ�� ���� ��������
int t; //�߰��� ���ڰ� ���󰡴� ���� ���� ��������
int lev;
int attack_tab;


void level();
void game(int*);
void gotoxy(int x, int y);
void show_string(char*, int*, int);
void c_hp_bar(int);
void b_hp_bar(int);
void picture();
unsigned _stdcall story(void* arg);
void mainmenu();
void diff(char *w);
void bomb(int);
void boss_damage();
void char_damage();
void finish(int);
void gameover(int);
void clear();
void boss_attack1(char* W);
void boss_attack2(char* W);
void boss_attack3(char* W);