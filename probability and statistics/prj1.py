import random

def solve_pi(n):
    in_circle = 0
    for _ in range(n):
        x = random.uniform(0, 1)
        y = random.uniform(0, 1)
        if(x - 0.5) ** 2 + (y -0.5) ** 2 <= 0.25:
            in_circle += 1
    return 4 * in_circle/n

n = 10000000
pi = solve_pi(n)
print("샘플", n, "개로 추정한 파이값",  pi)