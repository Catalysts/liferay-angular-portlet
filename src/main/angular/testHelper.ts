export function getMock(mockedClass): any {
    Object.getOwnPropertyNames(mockedClass.prototype).forEach((m) => {
        if (mockedClass.prototype[m]) {
            spyOn(mockedClass.prototype, m);
        }
    });
    return new mockedClass();
};
