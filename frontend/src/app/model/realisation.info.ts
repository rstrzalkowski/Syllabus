export interface RealisationInfoPage {
  content: RealisationInfo[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface RealisationInfo {
  id: number
  subjectName: string
  teacherId: number
  year: number
  teacherFirstName: string
  teacherLastName: string
  schoolClassName: string
  subjectAbbreviation: string
  imageUrl: string
}
