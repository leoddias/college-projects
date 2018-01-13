import sys
from math import acos, degrees
def main():
	out = degrees(acos((float(sys.argv[1]) * float(sys.argv[1]) + float(sys.argv[2]) * float(sys.argv[2]) - float(sys.argv[3]) * float(sys.argv[3]))/(2.0 * float(sys.argv[1]) * float(sys.argv[2]))))
	print 'Angulo-> ',out

if __name__ == '__main__':
  main()
