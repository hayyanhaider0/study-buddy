import base64

from app.models.request import ChapterCanvas

# async def run_ocr(chapters: list[ChapterCanvas]) -> dict[str, list[str]]:
#     result = {}
#     for chapter in chapters:
#         lines = []
#         for canvas in chapter.canvases:
#             lines.extend(await analyze_canvas(canvas))
#         result[chapter.chapterName] = lines
#     return result

async def run_ocr(chapters: list[ChapterCanvas]) -> dict[str, list[str]]:
    return {
        "Chapter 1 - Introduction to Java": [
            "What is Java?",
            "Java is a high-level, object-oriented programming language",
            "Write once, run anywhere - JVM",
            "Variables and Data Types",
            "int x = 5;",
            "double pi = 3.14;",
            "String name = \"Alice\";",
            "boolean isStudent = true;",
            "Naming conventions - camelCase for variables",
            "Constants declared with final keyword",
            "final int MAX = 100;",
        ],
        "Chapter 2 - Control Flow": [
            "If-else statements",
            "if (x > 0) {",
            "    System.out.println(\"Positive\");",
            "} else {",
            "    System.out.println(\"Negative\");",
            "}",
            "Switch statements",
            "switch (day) {",
            "    case 1: System.out.println(\"Monday\"); break;",
            "    case 2: System.out.println(\"Tuesday\"); break;",
            "}",
            "For loops - used when number of iterations is known",
            "for (int i = 0; i < 10; i++) {",
            "    System.out.println(i);",
            "}",
            "While loops - used when condition is unknown",
            "while (x > 0) { x--; }",
            "Do-while guarantees at least one execution",
        ],
        "Chapter 3 - Methods and Arrays": [
            "Methods - reusable blocks of code",
            "public static int add(int a, int b) {",
            "    return a + b;",
            "}",
            "Method overloading - same name different parameters",
            "public static double add(double a, double b) {",
            "    return a + b;",
            "}",
            "Arrays - fixed size collection of same type",
            "int[] arr = new int[5];",
            "arr[0] = 10; arr[1] = 20;",
            "Iterating arrays with for loop",
            "for (int i = 0; i < arr.length; i++) {",
            "    System.out.println(arr[i]);",
            "}",
            "2D arrays",
            "int[][] matrix = new int[3][3];",
            "matrix[0][0] = 1;",
        ]
    }

async def analyze_canvas(base64_canvas: str) -> list[str]:
    return base64.b64decode(base64_canvas).decode("utf-8")