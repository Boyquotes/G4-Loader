import sys

def my_function():
    '''
        wonder what's 'setup.py install"?
        $ python a.py my_function
    '''
    print("Hello from a function")

if __name__ == '__main__':
    # Now you can use this to call my_function
    # python a.py my_function
    globals()[sys.argv[1]]()
